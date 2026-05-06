package fr.n7.stl.minic.ast.expression.assignable;

import fr.n7.stl.minic.ast.expression.AbstractArray;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.minic.ast.instruction.declaration.VariableDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.Library;

/**
 * Abstract Syntax Tree node for an expression whose computation assigns a cell in an array.
 * @author Marc Pantel
 */
public class ArrayAssignment extends AbstractArray<AssignableExpression> implements AssignableExpression {

    /**
     * Construction for the implementation of an array element assignment expression Abstract Syntax Tree node.
     *
     * @param _array Abstract Syntax Tree for the array part in an array element assignment expression.
     * @param _index Abstract Syntax Tree for the index part in an array element assignment expression.
     */
    public ArrayAssignment(AssignableExpression _array, AccessibleExpression _index) {
        super(_array, _index);
    }

    /**
     * Gère la génération de code pour l'affectation t[i] = valeur.
     * La valeur à assigner est supposée être déjà sur la pile par l'instruction parente (Assignment)
     * ou gérée via une méthode de l'interface AssignableExpression.
     */
    @Override
    public Fragment getCode(TAMFactory factory) {
        Fragment fragment = factory.createFragment();

        if (this.array instanceof VariableAssignment) {
            VariableDeclaration decl =
                    (VariableDeclaration) ((VariableAssignment) this.array).getDeclaration();
            fragment.add(factory.createLoad(
                    decl.getRegister(),
                    decl.getOffset(),
                    1));
        } else {
            fragment.append(this.array.getCode(factory));
        }

        fragment.append(this.index.getCode(factory));
        int elemSize = this.getType().length();
        if (elemSize > 1) {
            fragment.add(factory.createLoadL(elemSize));
            fragment.add(Library.IMul);
        }
        fragment.add(Library.IAdd);
        return fragment;
    }
}