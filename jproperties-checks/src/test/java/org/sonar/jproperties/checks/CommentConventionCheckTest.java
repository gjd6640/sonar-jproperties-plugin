/*
 * SonarQube Java Properties Plugin
 * Copyright (C) 2015-2016 David RACODON
 * david.racodon@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.jproperties.checks;

import org.junit.Test;
import org.sonar.jproperties.checks.verifier.JavaPropertiesCheckVerifier;

import static org.fest.assertions.Assertions.assertThat;

public class CommentConventionCheckTest {

  private CommentConventionCheck check = new CommentConventionCheck();

  @Test
  public void should_only_contain_comments_starting_with_hash_token_and_not_raise_issues() {
    JavaPropertiesCheckVerifier.verify(check, TestUtils.getTestFile("commentsHashOnly.properties"));
  }

  @Test
  public void should_only_contain_comments_starting_with_exclamation_mark_token_and_not_raise_issues() {
    check.setStartingCommentToken("!");
    JavaPropertiesCheckVerifier.verify(check, TestUtils.getTestFile("commentsExclamationMarkOnly.properties"));
  }

  @Test
  public void should_contain_comments_starting_with_exclamation_mark_token_and_raise_issues() {
    JavaPropertiesCheckVerifier.verify(check, TestUtils.getTestFile("commentsHashAndExclamationMark.properties"));
  }

  @Test
  public void should_contain_comments_starting_with_hash_token_and_raise_issues() {
    check.setStartingCommentToken("!");
    JavaPropertiesCheckVerifier.verify(check, TestUtils.getTestFile("commentsExclamationMarkAndHash.properties"));
  }

  @Test
  public void should_throw_an_illegal_state_exception_as_the_starting_comment_token_parameter_is_not_valid() {
    try {
      check.setStartingCommentToken("abc");
      JavaPropertiesCheckVerifier.verify(check, TestUtils.getTestFile("commentsHashOnly.properties"));
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("Check jproperties:comment-convention (All comments should be formatted consistently): "
        + "startingCommentToken parameter is not valid.\nActual: 'abc'\nExpected: '#' or '!'");
    }

  }

}
