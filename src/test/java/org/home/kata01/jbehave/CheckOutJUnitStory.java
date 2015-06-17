package org.home.kata01.jbehave;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.StoryPathResolver;
import org.jbehave.core.junit.JUnitStory;

import java.util.Collections;

public abstract class CheckOutJUnitStory extends JUnitStory {
    @Override
    public void run() throws Throwable {
        Embedder embedder = new CheckOutEmbedder();
        StoryPathResolver pathResolver = embedder.configuration().storyPathResolver();
        String storyPath = pathResolver.resolve(getClass());

        try {
            embedder.runStoriesAsPaths(Collections.singletonList(storyPath));
        } finally {
            embedder.generateCrossReference();
        }
    }
}