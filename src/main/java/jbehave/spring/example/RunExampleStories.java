package jbehave.spring.example;

import io.tapack.allure.jbehave.AllureReporter;
import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.*;

/**
 * <p>
 * {@link Embeddable} class to run multiple textual stories via JUnit using
 * Spring Dependency Injection to compose the steps classes.
 * </p>
 * <p>
 * Stories are specified in classpath and correspondingly the
 * {@link LoadFromClasspath} story loader is configured.
 * </p>
 */
public class RunExampleStories extends JUnitStories {

    public static void main(String[] args) throws Throwable {
        new RunExampleStories().run();
    }

    public RunExampleStories() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true).useThreads(2).useStoryTimeoutInSecs(60);
    }

    @Override
    public Configuration configuration() {
        final Class<? extends Embeddable> embeddableClass = this.getClass();
        final ParameterConverters parameterConverters = new ParameterConverters();
        final ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(new LocalizedKeywords(),
                new LoadFromClasspath(embeddableClass), parameterConverters);
        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("yyyy-MM-dd")),
                new ExamplesTableConverter(examplesTableFactory));
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(embeddableClass))
                .useStoryParser(new RegexStoryParser(examplesTableFactory))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass)).withDefaultFormats()
                        .withFormats(CONSOLE, TXT, HTML, XML)
                        .withReporters(new AllureReporter()))
                .useParameterConverters(parameterConverters);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        final String path = "jbehave.spring.example".replaceAll("\\.", "/");
        final ApplicationContext context = new SpringApplicationContextFactory(path + "/applicationContext.xml")
                .createApplicationContext();
        return new SpringStepsFactory(configuration(), context);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story",
                "**/excluded*.story");
    }

}