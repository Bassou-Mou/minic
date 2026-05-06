/**
 * 
 */
package fr.n7.stl.minic.ast.expression.assignable;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.AbstractPointer;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.minic.ast.type.PointerType;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.util.Logger;


/**
 * Abstract Syntax Tree node for an expression whose computation assigns the content of a pointed cell.
 * @author Marc Pantel
 *
 */
public class PointerAssignment extends AbstractPointer<AccessibleExpression> implements AssignableExpression {

	/**
	 * Construction for the implementation of a pointer content assignment expression Abstract Syntax Tree node.
	 * @param _pointer Abstract Syntax Tree for the pointer expression in a pointer content assignment expression.
	 */
	public PointerAssignment(AccessibleExpression _pointer) {
		super(_pointer);
	}

    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> _scope) {return this.pointer.completeResolve(_scope);}

    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {return this.pointer.collectAndPartialResolve(_scope);}

    @Override
    public Type getType() {
        Type _pointerType = this.pointer.getType();
        if (_pointerType instanceof PointerType) {
            return ((PointerType) _pointerType).getPointedType();
        } else {
            Logger.error("Invalid dereference: expected a pointer type but got " + _pointerType);
            return AtomicType.ErrorType;
        }
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.PointerAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _fragment = this.pointer.getCode(_factory);
        _fragment.add(_factory.createStoreI(this.getType().length()));
        return _fragment;
    }
	
}
