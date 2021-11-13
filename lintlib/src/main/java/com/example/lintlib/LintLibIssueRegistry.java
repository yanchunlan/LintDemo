package com.example.lintlib;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;
import com.example.lintlib.dependency.DependencyDetector;
import com.example.lintlib.glide.GlideWithDetector;
import com.example.lintlib.log.LogDetector;

public class LintLibIssueRegistry extends IssueRegistry {

  @NotNull
  @Override
  public List<Issue> getIssues() {
    return Arrays.asList(
        LogDetector.ISSUE,
        GlideWithDetector.ISSUE,
        DependencyDetector.ISSUE
    );
  }
}