/*
 * Elijjah compiler, copyright Tripleo <oluoluolu+elijah@gmail.com>
 *
 * The contents of this library are released under the LGPL licence v3,
 * the GNU Lesser General Public License text was downloaded from
 * http://www.gnu.org/licenses/lgpl.html from `Version 3, 29 June 2007'
 *
 */
package tripleo.elijah_elevateder.stages.deduce;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tripleo.elijah.g.GResolveError;
import tripleo.elijah.lang.i.*;
import tripleo.elijah_fluffy.diagnostic.ElDiagnostic;
import tripleo.elijah_fluffy.diagnostic.ElLocatable;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created 12/26/20 5:08 AM
 */
public class ResolveError extends GResolveError implements ElDiagnostic {
	private final @org.jetbrains.annotations.Nullable IdentExpression ident;
	private final LookupResultList lrl;
	private final @org.jetbrains.annotations.Nullable TypeName typeName;

	public ResolveError(IdentExpression aIdent, LookupResultList aLrl) {
		ident = aIdent;
		lrl = aLrl;
		typeName = null;
	}

	public ResolveError(TypeName typeName, LookupResultList lrl) {
		this.typeName = typeName;
		this.lrl = lrl;
		this.ident = null;
	}

	@Override
	public @NotNull String code() {
		return "S1000";
	}

	private @NotNull String message() {
		if (resultsList().size() > 1)
			return "Can't choose between alternatives";
		else
			return "Can't resolve";
	}

	@Override
	public @NotNull ElLocatable primary() {
		if (typeName == null) {
			return ident;
		} else
			return typeName;
	}

	@Override
	public void report(@NotNull PrintStream stream) {
		stream.printf("---[%s]---: %s%n", code(), message());
		// linecache.print(primary);
		for (ElLocatable sec : secondary()) {
			// linecache.print(sec)
		}
		stream.flush();
	}

	@NotNull
	public List<LookupResult> resultsList() {
		return lrl.results();
	}

	@Override
	public @NotNull List<ElLocatable> secondary() {
		return resultsList().stream().map(e -> (ElLocatable) e.getElement()).collect(Collectors.toList());
	}

	@Override
	public @NotNull Severity severity() {
		return Severity.ERROR;
	}

	public @Nullable IdentExpression _ident() {
		return ident;
	}
}

//
//
//
