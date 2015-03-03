package org.hidetake.workflowssh;

import hudson.Extension;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.workflow.steps.AbstractStepDescriptorImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractStepImpl;
import org.jenkinsci.plugins.workflow.steps.AbstractSynchronousStepExecution;
import org.jenkinsci.plugins.workflow.steps.StepContextParameter;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.inject.Inject;

public class SshStep extends AbstractStepImpl {

    private final Object message;

    @DataBoundConstructor
    public SshStep(Object message) {
        this.message = message;
    }

    public String getMessage() {
        return message.toString();
    }

    @Extension
    public static class DescriptorImpl extends AbstractStepDescriptorImpl {

        public DescriptorImpl() {
            super(Execution.class);
        }

        @Override
        public String getFunctionName() {
            return "ssh";
        }

        @Override
        public String getDisplayName() {
            return "Execute SSH";
        }
    }

    public static class Execution extends AbstractSynchronousStepExecution<Void> {

        @Inject private transient SshStep step;
        @StepContextParameter private transient TaskListener listener;

        @Override protected Void run() throws Exception {
            listener.getLogger().println(step.getMessage());
            return null;
        }

        private static final long serialVersionUID = 1L;

    }

}
