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
package org.sonar.plugins.jproperties;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.check.Rule;
import org.sonar.jproperties.checks.CheckList;
import org.sonar.jproperties.checks.LineLengthCheck;

import static org.fest.assertions.Assertions.assertThat;

public class JavaPropertiesRulesDefinitionTest {

  @Test
  public void test() {
    JavaPropertiesRulesDefinition rulesDefinition = new JavaPropertiesRulesDefinition();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository("jproperties");

    assertThat(repository.name()).isEqualTo("SonarQube");
    assertThat(repository.language()).isEqualTo("jproperties");
    assertThat(repository.rules()).hasSize(CheckList.getChecks().size());

    RulesDefinition.Rule lineLengthRule = repository.rule(LineLengthCheck.class.getAnnotation(Rule.class).key());
    assertThat(lineLengthRule).isNotNull();
    assertThat(lineLengthRule.name()).isEqualTo(LineLengthCheck.class.getAnnotation(Rule.class).name());
  }

}
