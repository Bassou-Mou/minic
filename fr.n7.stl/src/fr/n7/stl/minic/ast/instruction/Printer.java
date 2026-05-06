/**
 * 
 */
package fr.n7.stl.minic.ast.instruction;

import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.minic.ast.type.ArrayType;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.TAMInstruction;

/**
 * Implementation of the Abstract Syntax Tree node for a printer instruction.
 * @author Marc Pantel
 *
 */
public class Printer implements Instruction {

	protected Expression parameter;

	public Printer(Expression _value) {
		this.parameter = _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "print " + this.parameter + ";\n";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
        return this.parameter.collectAndPartialResolve(_scope);
    }
	
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		return this.collectAndPartialResolve(_scope);
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
        return this.parameter.completeResolve(_scope);
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {return this.parameter.getType() != null;
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
        return 0;
	}
    private TAMInstruction getRightOut(AtomicType atomicType) {
        switch (atomicType) {
            case BooleanType:  return Library.BOut;
            case IntegerType:  return Library.IOut;
            case CharacterType: return Library.COut;
            case StringType:   return Library.SOut;
            default:           return Library.IOut;
        }
    }
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _result = _factory.createFragment();
        Type type = resolveBaseType(this.parameter.getType()); // ← traversée ArrayType
        _result.append(this.parameter.getCode(_factory));
        if (type instanceof AtomicType) {
            _result.add(getRightOut((AtomicType) type));
        } else {
            _result.add(Library.IOut);
        }
        _result.add(_factory.createLoadL(10));
        _result.add(Library.COut);
        _result.addComment("PRINT instruction for: " + this.parameter);
        return _result;
    }
    private Type resolveBaseType(Type type) {
        while (type instanceof ArrayType) {
            type = ((ArrayType) type).getType();
        }
        return type;
    }
}
