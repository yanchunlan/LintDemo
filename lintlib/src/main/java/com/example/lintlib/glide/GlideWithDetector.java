package com.example.lintlib.glide;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.intellij.psi.PsiMethod;

/**
 * author:  yanchunlan
 * date:  2021/11/13 17:36
 * desc:
 */
public class GlideWithDetector extends Detector implements Detector.UastScanner {
  private static final String TAG = "GlideWithDetector";

  public static final Issue ISSUE = Issue.create(
      "GlideWithDetector",
      "Glide.with不允许传入Application",
      "Glide.with不允许传入Application",
      Category.MESSAGES,
      7,
      Severity.WARNING,
      new Implementation(GlideWithDetector.class, Scope.JAVA_FILE_SCOPE)
  );

  @Nullable
  @Override
  public List<String> getApplicableMethodNames() {
    return Arrays.asList("with");
  }

  @Override
  public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node, @NotNull
      PsiMethod method) {
    boolean isMemberInClass =
        context.getEvaluator().isMemberInClass(method, "com.bumptech.glide.Glide");
    boolean isMemberInSubClassOf =
        context.getEvaluator().isMemberInSubClassOf(method, "com.bumptech.glide.Glide", true);
    if (isMemberInClass || isMemberInSubClassOf) {
//      System.out.println(TAG + " " + node.getValueArguments().stream().count());
      String obj = node.getValueArguments().get(0).asSourceString();
      if (obj.toLowerCase().contains("application".toLowerCase())) { //检验 application
        context.report(ISSUE, node, context.getLocation(node), "Glide.with不允许传入Application");
      }
    }
  }
}