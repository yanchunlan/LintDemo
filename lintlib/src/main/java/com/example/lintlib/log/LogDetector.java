package com.example.lintlib.log;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.uast.UCallExpression;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.util.UastExpressionUtils;

import com.android.tools.lint.client.api.UElementHandler;
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
public class LogDetector extends Detector implements Detector.UastScanner {
  private static final String TAG = "LogDetector";

  public static final Issue ISSUE = Issue.create(
      "LogDetector", //第一无二的id即可
      "may replace LogUtils", //描述信息
      "may replace LogUtils",     // 描述信息
      Category.MESSAGES,
      5,
      Severity.WARNING,
      new Implementation(LogDetector.class, Scope.JAVA_FILE_SCOPE)//文件类型意味着只扫描java文件
  );


  @Nullable
  @Override
  public List<String> getApplicableMethodNames() {
    return Arrays.asList("v", "d", "i", "w", "e", "wtf");
  }

  @Override
  public void visitMethodCall(@NotNull JavaContext context, @NotNull UCallExpression node,
      @NotNull PsiMethod method) {
    boolean isMemberInClass = context.getEvaluator().isMemberInClass(method, "android.util.Log");
    boolean isMemberInSubClassOf = context.getEvaluator().isMemberInSubClassOf(method,
        "android.util.Log", true);
//    String obj = node.getValueArguments().get(0).asSourceString();
//    System.out.println(TAG + " " + obj.getClass().getName());
    if (isMemberInClass || isMemberInSubClassOf) {
      context.report(ISSUE, node, context.getLocation(node), "请使用LogUtils替换Log");
    }
  }


  /**
   * 返回需要检查的AST节点的类型，返回类型被createUastHandler检查
   */
  /*@Nullable
  @Override
  public List<Class<? extends UElement>> getApplicableUastTypes() {
    return Collections.singletonList(UCallExpression.class);
  }

  @Nullable
  @Override
  public UElementHandler createUastHandler(@NotNull JavaContext context) {
    return new LogHandler(context);
  }

  class LogHandler extends UElementHandler {

    private final JavaContext context;

    LogHandler(JavaContext context) {
      this.context = context;
    }

    @Override
    public void visitCallExpression(@NotNull UCallExpression node) {
//      super.visitCallExpression(node);
      if (!UastExpressionUtils.isMethodCall(node)) {
        return;
      }
      try {
        if (node.getReceiver() != null
            && node.getMethodName() != null) {
          String methodName = node.getMethodName();
          if (methodName.equals("i")
              || methodName.equals("d")
              || methodName.equals("e")
              || methodName.equals("v")
              || methodName.equals("w")
              || methodName.equals("wtf")) {
            PsiMethod method = node.resolve();

            boolean isMemberInClass = context.getEvaluator().isMemberInClass(method, "android.util.Log");
            boolean isMemberInSubClassOf = context.getEvaluator().isMemberInSubClassOf(method,
                "android.util.Log", true);
            if (isMemberInClass || isMemberInSubClassOf) {
              context.report(ISSUE, node, context.getLocation(node), "请使用LogUtils替换Log");
            }

          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }*/
}
