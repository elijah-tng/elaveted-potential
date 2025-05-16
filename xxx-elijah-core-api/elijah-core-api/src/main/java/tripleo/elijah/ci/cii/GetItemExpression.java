package tripleo.elijah.ci.cii;

import tripleo.vendor.antlr277.Token;
import tripleo.elijah.ci.CiExpression;
import tripleo.elijah.ci.ExpressionKind;

public interface GetItemExpression extends CiExpression {
	@Override
	ExpressionKind getKind();

	CiExpression index();

	@Override
	boolean is_simple();

	void parens(Token lb, Token rb);
}
