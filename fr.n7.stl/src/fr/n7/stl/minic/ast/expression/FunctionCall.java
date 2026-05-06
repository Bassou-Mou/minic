/**
 * 
 */
package fr.n7.stl.minic.ast.expression;

import java.util.Iterator;
import java.util.List;
import fr.n7.stl.minic.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.util.Logger;
import fr.n7.stl.minic.ast.type.FunctionType;

/**
 * Abstract Syntax Tree node for a function call expression.
 * @author Marc Pantel
 *
 */
public class FunctionCall implements AccessibleExpression {

	/**
	 * Name of the called function.
	 * TODO : Should be an expression.
	 */
	protected String name;
	
	/**
	 * Declaration of the called function after name resolution.
	 * TODO : Should rely on the VariableUse class.
	 */
	protected FunctionDeclaration function;
	
	/**
	 * List of AST nodes that computes the values of the parameters for the function call.
	 */
	protected List<AccessibleExpression> arguments;
	
	/**
	 * @param _name : Name of the called function.
	 * @param _arguments : List of AST nodes that computes the values of the parameters for the function call.
	 */
	public FunctionCall(String _name, List<AccessibleExpression> _arguments) {
		this.name = _name;
		this.function = null;
		this.arguments = _arguments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
        String _result = ((this.function == null)?this.name:this.function.getName()) + "( ";
		Iterator<AccessibleExpression> _iter = this.arguments.iterator();
		if (_iter.hasNext()) {
			_result += _iter.next();
		}
		while (_iter.hasNext()) {
			_result += " ," + _iter.next();
		}
		return  _result + ")";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
        if (((HierarchicalScope<Declaration>) _scope).knows(this.name)) {
            Declaration _functionDeclaration = _scope.get(this.name);
            if (_functionDeclaration instanceof FunctionDeclaration) {
                this.function = (FunctionDeclaration) _functionDeclaration;
            } else {
                Logger.error(this.name + " is not a function.");
                return false;
            }
        } else {
            Logger.error("Function " + this.name + " has not been found.");
            return false;
        }
        boolean _isValid = true;
        for (AccessibleExpression _argument : this.arguments) {
            _isValid &= _argument.collectAndPartialResolve(_scope);
        }
        return _isValid;
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#resolve(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
// Dans fr.n7.stl.minic.ast.expression.FunctionCall
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
        boolean ok = true;

        for (AccessibleExpression argument : this.arguments) {
            ok &= argument.completeResolve(_scope);
        }

        if (this.function == null) {
            Logger.error("Semantic error: Function " + this.name + " not resolved.");
            return false;
        }

        List<ParameterDeclaration> params = this.function.getParameters();
        if (this.arguments.size() != params.size()) {
            Logger.error("Function call error: Number of arguments does not match for '"
                    + this.function.getName() + "'. Expected " + params.size()
                    + ", got " + this.arguments.size());
            ok = false;
        } else {
            for (int i = 0; i < this.arguments.size(); i++) {
                Type actualType   = this.arguments.get(i).getType();
                Type expectedType = params.get(i).getType();
                if (!actualType.compatibleWith(expectedType)) {
                    Logger.error("Function call error: Argument " + (i + 1)
                            + " type mismatch for '" + this.function.getName()
                            + "'. Expected " + expectedType + ", got " + actualType);
                    ok = false;
                }
            }
        }

        return ok;
    }
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
    public Type getType() {
        return this.function.getType();
    }
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _fragment = _factory.createFragment();
        for (AccessibleExpression _argument : this.arguments) {
            _fragment.append(_argument.getCode(_factory));
        }
        _fragment.add(_factory.createCall(this.function.label, Register.SB));
        return _fragment;
    }

}
