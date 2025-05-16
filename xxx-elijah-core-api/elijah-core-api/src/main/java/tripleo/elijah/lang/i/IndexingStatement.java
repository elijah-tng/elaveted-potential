package tripleo.elijah.lang.i;

import tripleo.vendor.antlr277.Token;

public interface IndexingStatement {
	void add(IndexingItem i);

	void setExprs(ExpressionList el);

	void setName(Token i1);
}
