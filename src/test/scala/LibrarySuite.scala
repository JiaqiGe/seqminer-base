/*
 * This Scala Testsuite was auto generated by running 'gradle init --type scala-library'
 * by 'jge' at '9/18/15 11:10 AM' with Gradle 2.4
 *
 * @author jge, @date 9/18/15 11:10 AM
 */

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends FunSuite {
  test("someLibraryMethod is always true") {
    def library = new Library()
    assert(library.someLibraryMethod)
  }
}
