package tripleo.elijah.comp.i;

import antlr.*;
import tripleo.elijah.comp.*;
import tripleo.elijah.g.*;
import tripleo.elijah.stages.logging.*;
import tripleo.elijah_durable_elevated.comp.internal.*;
import tripleo.elijah_durable_elevated.stages.deduce.fluffy.i.*;
import tripleo.elijah_elevated_durable.comp.*;

import java.util.*;

public interface ICompilationAccess {
	void addFunctionMapHook(GFunctionMapHook aFunctionMapHook);

	List<GFunctionMapHook> functionMapHooks();

	void addPipeline(final GPipelineMember pl);

	Compilation getCompilation();

	GPipeline internal_pipelines();

	void setPipelineLogic(final GPipelineLogic pl);

	ElLog.Verbosity testSilence();

	GCompilationEnclosure getCompilationEnclosure();

	/// stop using Compilation
	FluffyComp getFluffy();

	/// convenience
	LCM lcm();
}
