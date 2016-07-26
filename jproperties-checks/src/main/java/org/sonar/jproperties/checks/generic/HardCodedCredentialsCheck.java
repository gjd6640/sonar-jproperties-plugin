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
package org.sonar.jproperties.checks.generic;

import java.util.regex.Pattern;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.jproperties.checks.Tags;
import org.sonar.plugins.jproperties.api.tree.KeyTree;
import org.sonar.plugins.jproperties.api.visitors.DoubleDispatchVisitorCheck;
import org.sonar.squidbridge.annotations.ActivatedByDefault;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

@Rule(
  key = "S2068",
  name = "Credentials should not be hard-coded",
  priority = Priority.CRITICAL,
  tags = {Tags.SECURITY, Tags.CWE, Tags.OWASP_A2, Tags.SANS_TOP25_POROUS})
@SqaleConstantRemediation("30min")
@ActivatedByDefault
public class HardCodedCredentialsCheck extends DoubleDispatchVisitorCheck {

  private static final Pattern HARD_CODED_USERNAME = Pattern.compile(".*(login|username).*", Pattern.CASE_INSENSITIVE);
  private static final Pattern HARD_CODED_PASSWORD = Pattern.compile(".*(password|passwd|pwd).*", Pattern.CASE_INSENSITIVE);

  @Override
  public void visitKey(KeyTree tree) {
    if (HARD_CODED_USERNAME.matcher(tree.text()).matches()) {
      addPreciseIssue(tree, "Remove this hard-coded username.");
    }
    if (HARD_CODED_PASSWORD.matcher(tree.text()).matches()) {
      addPreciseIssue(tree, "Remove this hard-coded password.");
    }
  }

}
