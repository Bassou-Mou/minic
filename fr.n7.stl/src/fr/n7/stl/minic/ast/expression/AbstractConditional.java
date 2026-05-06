/**
 * 
 */
package fr.n7.stl.minic.ast.expression;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Abstract Syntax Tree node for a conditional expression.
 * @author Marc Pantel
 *
 */
public class AbstractConditional<ExpressionKind extends Expression> implements Expression {

	/**
	 * AST node for the expression whose value is the condition for the conditional expression.
	 */
	protected Expression condition;
	
	/**
	 * AST node for the expression whose value is the then parameter for the conditional expression.
	 */
	protected ExpressionKind thenExpression;
	
	/**
	 * AST node for the expression whose value is the else parameter for the conditional expression.
	 */
	protected ExpressionKind elseExpression;
	
	/**
	 * Builds a binary expression Abstract Syntax Tree node from the left and right sub-expressions
	 * and the binary operation.
	 * @param _left : Expression for the left parameter.
	 * @param _operator : Binary Operator.
	 * @param _right : Expression for the right parameter.
	 */
	public AbstractConditional(Expression _condition, ExpressionKind _then, ExpressionKind _else) {
		this.condition = _condition;
		this.thenExpression = _then;
		this.elseExpression = _else;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
        boolean ok = this.condition.collectAndPartialResolve(_scope);
        ok &= this.thenExpression.collectAndPartialResolve(_scope);
        ok &= this.elseExpression.collectAndPartialResolve(_scope);
        return ok;
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
    @Override
    public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
        boolean ok = this.condition.completeResolve(_scope);
        ok &= this.thenExpression.completeResolve(_scope);
        ok &= this.elseExpression.completeResolve(_scope);
        return ok;
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.condition + " ? " + this.thenExpression + " : " + this.elseExpression + ")";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
    @Override
    public Type getType() {
        return this.thenExpression.getType().merge(this.elseExpression.getType());
    }

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        int num = _factory.createLabelNumber();
        Fragment _fragment = _factory.createFragment();
        _fragment.append(this.condition.getCode(_factory));
        _fragment.add(_factory.createJumpIf("cond_else_" + num, 0));
        _fragment.append(this.thenExpression.getCode(_factory));
        _fragment.add(_factory.createJump("cond_end_" + num));
        Fragment elseCode = this.elseExpression.getCode(_factory);
        elseCode.addPrefix("cond_else_" + num);
        _fragment.append(elseCode);
        _fragment.addSuffix("cond_end_" + num);
        return _fragment;
    }
}
