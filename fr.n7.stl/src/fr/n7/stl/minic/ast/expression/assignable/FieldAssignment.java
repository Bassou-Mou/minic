/**
 * 
 */
package fr.n7.stl.minic.ast.expression.assignable;

import fr.n7.stl.minic.ast.expression.AbstractField;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;

/**
 * Abstract Syntax Tree node for an expression whose computation assigns a field in a record.
 * @author Marc Pantel
 *
 */
public class FieldAssignment extends AbstractField<AssignableExpression> implements AssignableExpression {

    /**
     * Construction for the implementation of a record field assignment expression Abstract Syntax Tree node.
     *
     * @param _record Abstract Syntax Tree for the record part in a record field assignment expression.
     * @param _name   Name of the field in the record field assignment expression.
     */
    public FieldAssignment(AssignableExpression _record, String _name) {
        super(_record, _name);
    }

    /* (non-Javadoc)
     * @see fr.n7.stl.block.ast.impl.FieldAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
     */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        Fragment _result = _factory.createFragment();
        int fieldOffset = this.field.getOffset();
        int fieldSize = this.field.getType().length();
        if (this.record instanceof VariableAssignment) {
            fr.n7.stl.minic.ast.instruction.declaration.VariableDeclaration decl =
                    (fr.n7.stl.minic.ast.instruction.declaration.VariableDeclaration)
                            ((VariableAssignment) this.record).getDeclaration();
            _result.add(_factory.createLoadA(decl.getRegister(), decl.getOffset()));
        } else if (this.record instanceof AccessibleExpression) {
            _result.append(((AccessibleExpression) this.record).getCode(_factory));
        }

        if (fieldOffset > 0) {
            _result.add(_factory.createLoadL(fieldOffset));
            _result.add(Library.IAdd);
        }
        _result.add(_factory.createStoreI(fieldSize));
        return _result;
    }
}
