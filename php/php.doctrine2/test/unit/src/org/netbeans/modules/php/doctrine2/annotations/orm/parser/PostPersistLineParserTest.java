/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.php.doctrine2.annotations.orm.parser;

import java.util.Map;
import org.netbeans.junit.NbTestCase;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.php.spi.annotation.AnnotationLineParser;
import org.netbeans.modules.php.spi.annotation.AnnotationParsedLine;

/**
 *
 * @author Ondrej Brejla <obrejla@netbeans.org>
 */
public class PostPersistLineParserTest extends NbTestCase {
    private SimpleAnnotationLineParser parser;

    public PostPersistLineParserTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.parser = new SimpleAnnotationLineParser();
    }

    public void testIsAnnotationParser() throws Exception {
        assertTrue(parser instanceof AnnotationLineParser);
    }

    public void testReturnValueIsPostPersistParsedLine_01() throws Exception {
        assertTrue(parser.parse("PostPersist") instanceof AnnotationParsedLine.ParsedLine);
    }

    public void testReturnValueIsPostPersistParsedLine_02() throws Exception {
        assertTrue(parser.parse("Annotations\\PostPersist") instanceof AnnotationParsedLine.ParsedLine);
    }

    public void testReturnValueIsPostPersistParsedLine_03() throws Exception {
        assertTrue(parser.parse("\\Foo\\Bar\\PostPersist") instanceof AnnotationParsedLine.ParsedLine);
    }

    public void testReturnValueIsNull() throws Exception {
        assertNull(parser.parse("PostPersists"));
    }

    public void testValidUseCase_01() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("PostPersist");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 11));
        assertEquals("PostPersist", type1);
    }

    public void testValidUseCase_02() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("PostPersist   ");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 11));
        assertEquals("PostPersist", type1);
    }

    public void testValidUseCase_03() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("PostPersist\t\t  ");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 11));
        assertEquals("PostPersist", type1);
    }

    public void testValidUseCase_05() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("Annotations\\PostPersist  \t");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 23));
        assertEquals("Annotations\\PostPersist", type1);

    }

    public void testValidUseCase_06() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("\\Foo\\Bar\\PostPersist  \t");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 20));
        assertEquals("\\Foo\\Bar\\PostPersist", type1);
    }

    public void testValidUseCase_07() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("postpersist");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 11));
        assertEquals("postpersist", type1);
    }

    public void testValidUseCase_08() throws Exception {
        AnnotationParsedLine parsedLine = parser.parse("\\Foo\\Bar\\postpersist  \t");
        assertEquals("PostPersist", parsedLine.getName());
        assertEquals("", parsedLine.getDescription());
        Map<OffsetRange, String> types = parsedLine.getTypes();
        assertNotNull(types);
        assertEquals(1, types.size());
        String type1 = types.get(new OffsetRange(0, 20));
        assertEquals("\\Foo\\Bar\\postpersist", type1);
    }

}
