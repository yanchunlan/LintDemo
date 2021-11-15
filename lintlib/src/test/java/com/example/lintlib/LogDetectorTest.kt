package com.example.lintlib

import com.android.tools.lint.checks.infrastructure.LintDetectorTest
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Issue
import com.example.lintlib.log.LogDetector
import java.io.File
import java.util.*

/**
 * author:  yanchunlan
 * date:  2021/11/14 16:55
 * desc:  lint 测试
 */
class LogDetectorTest : LintDetectorTest() {
  override fun getDetector(): Detector {
    return LogDetector()
  }

  override fun getIssues(): List<Issue> {
    return Arrays.asList(
        LogDetector.ISSUE
    )
  }

  fun test() {
    lint()
        .files(
            kotlin(File("./src/test/java/com/example/lintlib/Test.kt")
                .readText())
        ).run()
        .expect(
            "No warnings"
        )
  }
}