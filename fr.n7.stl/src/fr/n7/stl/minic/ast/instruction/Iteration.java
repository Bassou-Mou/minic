/**
 *
 */
package fr.n7.stl.minic.ast.instruction;

import fr.n7.stl.minic.ast.Block;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.scope.SymbolTable;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.util.Logger;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional
 * instruction.
 *
 * @author Marc Pantel
 *
 */
public class Iteration implements Instruction {

    private Expression condition;
    private Block body;

    public Iteration(Expression condition, Block body) {
        this.condition = condition;
        this.body = body;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "while (" + this.condition + " )" + this.body;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope
     * .Scope)
     */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> scope) {
        SymbolTable bodyScope = new SymbolTable(scope);
        boolean okCond = condition.collectAndPartialResolve(scope);
        boolean okBody = body.collectAndPartialResolve(bodyScope);
        return okCond && okBody;
    }

    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> scope, FunctionDeclaration container) {
        SymbolTable bodyScope = new SymbolTable(scope);
        boolean okCond = condition.collectAndPartialResolve(scope);
        boolean okBody = body.collectAndPartialResolve(bodyScope, container);
        return okCond && okBody;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope
     * .Scope)
     */
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> scope) {
        SymbolTable bodyScope = new SymbolTable(scope);
        boolean okCond = condition.completeResolve(scope);
        boolean okBody = body.completeResolve(bodyScope);
        return okCond && okBody;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Instruction#checkType()
     */
    @Override
    public boolean checkType() {
        Type condType = this.condition.getType();
        boolean okBody = this.body.checkType();
        if (condType.equals(AtomicType.BooleanType)) {
            return okBody;
        }
        Logger.error("[Iteration] Wrong condition type");
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register,
     * int)
     */
    @Override
    public int allocateMemory(Register register, int offset) {
        body.allocateMemory(register, offset);
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
     */
    @Override
    public Fragment getCode(TAMFactory factory) {
        int num = factory.createLabelNumber();
        Fragment fragment = factory.createFragment();
        fragment.append(condition.getCode(factory));
        fragment.addPrefix("debut_boucle_" + num);
        fragment.add(factory.createJumpIf("fin_boucle_" + num, 0));
        Fragment bodyCode = body.getCode(factory);
        fragment.append(bodyCode);
        fragment.add(factory.createJump("debut_boucle_" + num));
        fragment.addSuffix("fin_boucle_" + num);
        // fragment.addComment(toString());
        return fragment;
    }

}