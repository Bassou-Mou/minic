/**
 *
 */
package fr.n7.stl.minic.ast.type;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;

/**
 * Implementation of the Abstract Syntax Tree node for a function type.
 *
 * @author Marc Pantel
 *
 */
public class FunctionType implements Type {

    private Type result;
    private List<Type> parameters;

    public FunctionType(Type result, Iterable<Type> parameters) {
        this.result = result;
        this.parameters = new LinkedList<Type>();
        for (Type type : parameters) {
            this.parameters.add(type);
        }
    }

    /**
     * Returns the list of parameter types for this function.
     * @return List of parameter types
     */
    public List<Type> getParameterTypes() {
        return this.parameters;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
     */
    @Override
    public boolean equalsTo(Type other) {
        if (!(other instanceof FunctionType)) {
            return false;
        }

        FunctionType function = (FunctionType) other;
        if (!this.result.equalsTo(function.result)) {
            return false;
        }
        if (this.parameters.size() != function.parameters.size()) {
            return false;
        }

        boolean okParamTypes = true;
        for (int i = 0; i < this.parameters.size(); i++) {
            okParamTypes = okParamTypes && this.parameters.get(i).equalsTo(function.parameters.get(i));
        }
        return okParamTypes;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
     */
    @Override
    public boolean compatibleWith(Type other) {
        if (!(other instanceof FunctionType)) {
            return false;
        }
        FunctionType function = (FunctionType) other;
        if (!this.result.compatibleWith(function.result)) {
            return false;
        }

        if (this.parameters.size() != function.parameters.size()) {
            return false;
        }
        boolean okParamTypes = true;
        for (int i = 0; i < this.parameters.size(); i++) {
            okParamTypes = okParamTypes && function.parameters.get(i).compatibleWith(this.parameters.get(i));
        }
        return okParamTypes;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
     */
    @Override
    public Type merge(Type other) {
        throw new SemanticsUndefinedException("Semantics merge is undefined in FunctionType.");
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.Type#length(int)
     */
    @Override
    public int length() {
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String result = "(";
        Iterator<Type> iter = this.parameters.iterator();
        if (iter.hasNext()) {
            result += iter.next();
            while (iter.hasNext()) {
                result += " ," + iter.next();
            }
        }
        return result + ") -> " + this.result;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.n7.stl.block.ast.type.Type#resolve(fr.n7.stl.block.ast.scope.Scope)
     */
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> scope) {
        boolean ok = this.result.completeResolve(scope);
        for (Type type : this.parameters) {
            ok = ok && type.completeResolve(scope);
        }
        return ok;
    }

}