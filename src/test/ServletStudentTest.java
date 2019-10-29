package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.ServletStudent;

class ServletStudentTest {

	MockHttpServletRequest request;
	MockHttpServletResponse response;
	ServletStudent servlet;
	HttpServletRequest req;
	HttpServletResponse res;
	
	@BeforeEach
	void setUp() throws Exception {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@AfterEach
	void tearDown() throws Exception {
		request = null;
		response = null;
	}

	
	/*
	 * Test case TC_1.0_1
	 * Parametro: nome
	 * Verifica formato nome non valido
	 */
	
	@Test
	void test1() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco12");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());

	}
	
	
	/*
	 * Test case TC_1.0_2
	 * Parametro: nome
	 * Verifica lunghezza nome (lunghezza < 1)
	 */
	
	@Test
	void test2() {
		request.addParameter("flag", "1");
		request.addParameter("name", "");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	/*
	 * Test case TC_1.0_3
	 * Parametro: nome
	 * Verifica lunghezza nome (lunghezza > 20)
	 */
	
	@Test
	void test3() {
		request.addParameter("flag", "1");
		request.addParameter("name", "MarcoAndreaMicheleAntonioLuigi");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_4
	 * Parametro: cognome
	 * Verifica formato cognome non valido
	 */

	@Test
	void test4() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi12");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_5
	 * Parametro: cognome
	 * Verifica lunghezza cognome (lunghezza < 1)
	 */
	
	@Test
	void test5() {
		request.addParameter("flag", "1");
		request.addParameter("name", "MarcoAndreaMicheleAntonioLuigi");
		request.addParameter("surname", "");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_6
	 * Parametro: cognome
	 * Verifica lunghezza cognome (lunghezza > 20)
	 */
	
	@Test
	void test6() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "RossiVerdiGialliBianchiBruni");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_7
	 * Parametro: email
	 * Verifica formato prefisso email non valido
	 */
	
	@Test
	void test7() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_8
	 * Parametro: email
	 * Verifica formato dominio email non valido
	 */
	
	@Test
	void test8() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@gmail.com");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_9
	 * Parametro: email
	 * Verifica presenza email nel database
	 */
	
	@Test
	void test9() {
		req = mock(HttpServletRequest.class);
		res = mock(HttpServletResponse.class);
		
		File file = new File(".\\tmp");
		PrintWriter out;
		String errore = "";
		String message = "Utente gi&agrave; registrato.";
		
		when(req.getParameter("flag")).thenReturn("1");
		when(req.getParameter("name")).thenReturn("VMarco");
		when(req.getParameter("surname")).thenReturn("Rossi");
		when(req.getParameter("email")).thenReturn("e.tesauro3@studenti.unisa.it");
		when(req.getParameter("sex")).thenReturn("M");
		when(req.getParameter("password")).thenReturn("Password123");
		when(req.getParameter("verifyPassword")).thenReturn("Password123");
		
		try {
			out = new PrintWriter(file);
			when(res.getWriter()).thenReturn(out);
			new ServletStudent().doPost(req, res);
			out.flush();
			out.close();
			@SuppressWarnings("resource")
			String reader = new BufferedReader(new FileReader(file)).readLine();
			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject) parser.parse(reader);
			errore = (String) result.get("error");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(message, errore);
	}
	
	
	/*
	 * Test case TC_1.0_10
	 * Parametro: sesso
	 * Verifica formato sesso non valido
	 */
	
	@Test
	void test10() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "G");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Valore non corretto";

		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_11
	 * Parametro: password
	 * Verifica formato password non valido
	 */
	
	/*
	 * Nota: il test case ha successo perchè non viene effettuato nessun controllo sul formato
	 * della password ma viene controllata la sua lunghezza. Per questo motivo si segnala
	 * questa incongruenza in quanto la password potrebbe non rispettare il formato stabilito
	 * ma il sistema non segnalerà nessun errore.
	 */
	
	@Test
	void test11() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password...&123");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";

		assertEquals(message, exceptionThrown.getMessage());

	}
	
	
	/*
	 * test case TC_1.0_12 tast case - parametro password: Verifica lunghezza
	 * password (lunghezza < 8)
	 * 
	 * Test case TC_1.0_12
	 * Parametro: password
	 * Verifica lunghezza password (lunghezza < 8)
	 */
	
	@Test
	void test12() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Pw");
		request.addParameter("verifyPassword", "Password123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_13
	 * Parametri: *Password = Password123, *verifyPassword = Password456
	 * Verifica corrispondenza password
	 */

	/*
	 * Nota: il test case ha successo perchè non viene effettuato nessun controllo lato
	 * server del campo verifyPassword.
	 * Nonostante verifyPassword venga trasferita al server, si presuppone che vengano 
	 * fatti solo controlli lato client sulla verifica della password. Perciò si segnala 
	 * incongruenza in quanto, data tale omissione di controllo lato server, alcune registrazioni
	 * potrebbero andare a buon fine nonostante la disuguaglianza tra password e verifyPassword.
	 */
	
	@Test
	void test13() {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Pass123");
		
		IllegalArgumentException exceptionThrown = assertThrows(IllegalArgumentException.class,()-> {
			new ServletStudent().doPost(request, response);
		});
		
		final String message = "Formato non corretto";
		assertEquals(message, exceptionThrown.getMessage());
	}
	
	
	/*
	 * Test case TC_1.0_14
	 * Test successo
	 */
	
	@Test
	void test14() throws ServletException, IOException {
		request.addParameter("flag", "1");
		request.addParameter("name", "Marco");
		request.addParameter("surname", "Rossi");
		request.addParameter("email", "m.rossi@studenti.unisa.it");
		request.addParameter("sex", "M");
		request.addParameter("password", "Password123");
		request.addParameter("verifyPassword", "Password123");
		
		new ServletStudent().doPost(request, response);
		
		assertEquals("json", response.getContentType());
	}

}
