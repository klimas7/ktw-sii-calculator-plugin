package io.jenkins.plugins.calculatorPlugin;

import java.io.IOException;
import javax.annotation.Nonnull;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Descriptor;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

public class CalculatorBuilder extends Builder implements SimpleBuildStep {

    @DataBoundConstructor
    public CalculatorBuilder() {

    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
        listener.getLogger().println("Calculation results: ");
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        private NumericalSystem numericalSystem = NumericalSystem.HEX;

        public DescriptorImpl() {
            load();
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Calculate";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject json) throws FormException {
            numericalSystem = NumericalSystem.valueOf((String) json.get("numericalSystem"));
            save();
            return super.configure(req, json);
        }

        public NumericalSystem getNumericalSystem() {
            return numericalSystem;
        }
    }

}
