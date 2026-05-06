/**
 * 
 */
package fr.n7.stl.minic.ast.instruction;

import java.util.Optional;

import fr.n7.stl.minic.ast.Block;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.util.Logger;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional instruction.
 * @author Marc Pantel
 *
 */
public class Conditional implements Instruction {

	protected Expression condition;
	protected Block thenBranch;
	protected Block elseBranch;

	public Conditional(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = _else;
	}

	public Conditional(Expression _condition, Block _then) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "if (" + this.condition + " )" + this.thenBranch + ((this.elseBranch != null)?(" else " + this.elseBranch):"");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
        boolean _conditionResolved = this.condition.collectAndPartialResolve(_scope);
        boolean _thenResolved = this.thenBranch.collectAndPartialResolve(_scope);
        boolean _elseResolved = true;

        if (this.elseBranch != null) {
            _elseResolved = this.elseBranch.collectAndPartialResolve(_scope);
        }

        return _conditionResolved && _thenResolved && _elseResolved;
    }
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
        boolean _conditionResolved = this.condition.collectAndPartialResolve(_scope);
        boolean _thenResolved = this.thenBranch.collectAndPartialResolve(_scope, _container);
        boolean _elseResolved = true;

        if (this.elseBranch != null) {
            _elseResolved = this.elseBranch.collectAndPartialResolve(_scope, _container);
        }

        return _conditionResolved && _thenResolved && _elseResolved;
    }
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
        boolean _conditionResolved = this.condition.completeResolve(_scope);
        boolean _thenResolved = this.thenBranch.completeResolve(_scope);
        boolean _elseResolved = true;

        if (this.elseBranch != null) {
            _elseResolved = this.elseBranch.completeResolve(_scope);
        }

        return _conditionResolved && _thenResolved && _elseResolved;
    }


    /* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
    @Override
    public boolean checkType() {
        boolean _conditionResolved = this.condition.getType().compatibleWith(AtomicType.BooleanType);

        if (!_conditionResolved) {
            Logger.error("Conditional type error: expected condition of type boolean but got "
                    + this.condition.getType());
        }

        boolean _thenResolved = this.thenBranch.checkType();
        boolean _elseResolved = true;

        if (this.elseBranch != null) {
            _elseResolved = this.elseBranch.checkType();
        }

        return _conditionResolved && _thenResolved && _elseResolved;
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
    @Override
    public int allocateMemory(Register _register, int _offset) {
        this.thenBranch.allocateMemory(_register, _offset);
        if (this.elseBranch != null) {
            this.elseBranch.allocateMemory(_register, _offset);
        }
        return 0;
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
    @Override
    public Fragment getCode(TAMFactory _factory) {

        Fragment _result = this.condition.getCode(_factory);
        int _number = _factory.createLabelNumber();
        String _elseLabel = "else_" + _number;
        String _endLabel = "end_if_" + _number;

        if (this.elseBranch != null) {
            _result.add(_factory.createJumpIf(_elseLabel, 0));
        } else {
            _result.add(_factory.createJumpIf(_endLabel, 0));
        }

        _result.append(this.thenBranch.getCode(_factory));
        if (this.elseBranch != null) {
            _result.add(_factory.createJump(_endLabel));

            Fragment _elseFragment = this.elseBranch.getCode(_factory);
            _elseFragment.addPrefix(_elseLabel);
            _result.append(_elseFragment);
        }

        _result.add(_factory.createPop(0, 0));
        _result.addSuffix(_endLabel);
        _result.addComment("IF statement with condition: " + this.condition);

        return _result;
    }

}
