/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.git80;

import bind.AbstractBaseTest;
import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class TestRuntimeGit80.
 */
public class TestRuntimeGit80 extends AbstractBaseTest {

  public static final String MESSAGE_8859_TXT = "src/test/resources/bind/git80/message_8859.txt";

  /**
   * Test run.
   *
   * @throws Exception the exception
   */
  @Test
  public void testReadWithReplace() throws Exception {
    BinderContext binder = KriptonBinder.bind(BinderType.JSON);
    Message result = readMessage(binder, MESSAGE_8859_TXT, CodingErrorAction.IGNORE);

    assertNotNull(result);
    assertEquals("Si  verificato un errore imprevisto. Se il problema dovesse persistere contattare i fornitori del servizio.", result.getErrori().getDescrizione());
  }

  /**
   * Expected kripton runtime exception with cause.
   *
   * @param <E>            the element type
   * @param clazzException the clazz exception
   * @throws InstantiationException the instantiation exception
   * @throws IllegalAccessException the illegal access exception
   */
  public <E> void expectedKriptonRuntimeExceptionWithCause(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
    expectedEx.expect(KriptonRuntimeException.class);
    expectedEx.expectMessage(clazzException.getSimpleName());
  }

  @Test
  public void testReadWithReport() throws Exception {
    expectedKriptonRuntimeExceptionWithCause(MalformedInputException.class);
    BinderContext binder = KriptonBinder.bind(BinderType.JSON);

    Message result = readMessage(binder, MESSAGE_8859_TXT, CodingErrorAction.REPORT);

    assertNotNull(result);
  }

  private Message readMessage(BinderContext binder, String fileName, CodingErrorAction report) throws FileNotFoundException {
    File input = new File(fileName);
    CharsetDecoder utf8Decoder = StandardCharsets.UTF_8.newDecoder();
    utf8Decoder.onMalformedInput(report);
    utf8Decoder.onUnmappableCharacter(report);
    InputStreamReader targetStream = new InputStreamReader(new FileInputStream(input), utf8Decoder);
    return binder.parse(targetStream, Message.class);
  }

}
