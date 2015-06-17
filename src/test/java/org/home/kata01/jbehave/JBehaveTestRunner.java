package org.home.kata01.jbehave;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.junit.Test;

import java.util.List;

public class JBehaveTestRunner {
    @Test
    public void runClasspathLoadedStoriesAsJUnit() {
        List<String> storyPaths = new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"),
                                                              "**/*.story",
                                                              "");
        Embedder embedder = new CheckOutEmbedder();
        embedder.runStoriesAsPaths(storyPaths);
    }
}