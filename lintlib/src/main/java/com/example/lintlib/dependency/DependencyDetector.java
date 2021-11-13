package com.example.lintlib.dependency;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Context;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Project;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;


/**
 * author:  yanchunlan
 * date:  2021/11/13 17:36
 * desc:
 */
public class DependencyDetector extends Detector implements Detector.GradleScanner {

  public static final Issue ISSUE = Issue.create(
      "DependencyDetector",
      "组件依赖关系",
      "组件依赖关系",
      Category.MESSAGES,
      5,
      Severity.WARNING,
      new Implementation(DependencyDetector.class, EnumSet.of(Scope.GRADLE_FILE))
  );

  private TreeNode mTreeNode;
  private StringBuffer stringBuffer;

  @Override
  public void beforeCheckRootProject(@NotNull Context context) {
    mTreeNode = getNodes(context.getMainProject());
    super.beforeCheckRootProject(context);
    stringBuffer = new StringBuffer();
    printNode(mTreeNode);
    System.out.println(stringBuffer.toString());
  }


  // 创建一个TreeNode 数据结构    递归填充个节点
  public TreeNode getNodes(Project project) {
    List<Project> projects = project.getDirectLibraries();//获取子project，肯定是包括我们的依赖module
    List<TreeNode> nodes = new ArrayList<>();
    List<String> strings = new ArrayList<>();//存在多次扫描的情况
    TreeNode mTreeNode = new TreeNode(project.getName(), nodes);
    if (projects == null || projects.size() == 0) {
      return mTreeNode;
    }
    for (int i = 0; i < projects.size(); i++) {
      Project mProject = projects.get(i);
      if (mProject.isGradleProject() && !strings.contains(mProject.getName())) {
        nodes.add(getNodes(mProject));
        strings.add(mProject.getName());
      }
    }
    return mTreeNode;
  }

  //将组件组装成,其中A,B 代表module名称
  //  A-->B
  //  A-->c
  //  B-->D
  //  B-->C
  public void printNode(TreeNode mTreeNode) {
    for (int i = 0; i < mTreeNode.getChidrenNodes().size(); i++) {
      TreeNode childTreeNode = mTreeNode.getChidrenNodes().get(i);
      stringBuffer
          .append("" + mTreeNode.getCurrentName() + "-->" + childTreeNode.getCurrentName() + "\n");
      printNode(childTreeNode);
    }
  }
}