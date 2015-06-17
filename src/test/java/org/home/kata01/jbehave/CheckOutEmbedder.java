package org.home.kata01.jbehave;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CasePreservingResolver;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class CheckOutEmbedder extends Embedder {
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(getClass()))
                .useStoryPathResolver(new CasePreservingResolver())
                .useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.XML,
                                                                                Format.IDE_CONSOLE,
                                                                                Format.CONSOLE,
                                                                                Format.HTML,
                                                                                Format.TXT)
                                                                   .withRelativeDirectory("../build/jbehave/"));
        /*  The default output for the tests require is Maven's "target" folder,  since we're using Gradle we will set it to target/../build/jbehave */
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new CheckOutSteps());
    }
}