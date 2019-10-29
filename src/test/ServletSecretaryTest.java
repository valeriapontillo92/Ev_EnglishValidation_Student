package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.sql.PreparedStatement;

import controller.DbConnection;
import controller.ServletSecretary;
import model.Utils;

class ServletSecretaryTest{
	
	MockHttpServletRequest request;
	MockHttpServletResponse response;
	ServletSecretary servlet = new ServletSecretary();
	static Connection conn = DbConnection.getInstance().getConn();

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
	 * Test0
	 * Parametro: flag = 0
	 * Verifica il comportamento della servlet per un valore di flag non valido.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test() throws ServletException, IOException {
		request.addParameter("flag", "0");
						
		servlet.doGet(request, response);
	}
	
	/*
	 * Per permettere alla servlet di comportarsi secondo il modo stabilito dallo
	 * sviluppatore sono stato costretto a dover sostituire il valore "null" che
	 * veniva passato in input al metodo "executeQuery(null)" con la stringa "sql",
	 * ovvero la query che deve essere eseguita.
	 */
	
	/*
	 * Test1
	 * Parametro: flag = 1
	 * Creo un utente e una richiesta (con id=2) e verifico il comportamento della servlet.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test1() throws ServletException, IOException {
		request.addParameter("flag", "1");
		
		inserisciUtente();
		inserisciRequest2();
		
		servlet.doGet(request, response);
		
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	/*
	 * Test2
	 * Parametro: flag = 1
	 * Creo un utente e una richiesta (con id=1) e verifico il comportamento della servlet.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test2() throws ServletException, IOException {
		request.addParameter("flag", "1");
		
		inserisciUtente();
		inserisciRequest1();
		
		servlet.doGet(request, response);
		
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	/*
	 * Test3
	 * Parametro: flag = 1
	 * Creo un utente, una richiesta (con id=2), simulo l'invio dei due documenti
	 * e verifico il comportamento della servlet.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test3() throws ServletException, IOException, SQLException {
		request.addParameter("flag", "1");
		
		inserisciUtente();
		inserisciRequest2();
		inserisciAttached1();
		inserisciAttached2();
		
		servlet.doGet(request, response);
		
		removeAttached();
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	/*
	 * Nota: Per far funzionare i test che prevedono il valore di flag = 2 ho dovuto
	 * commentare l'istruzione presente subito dopo il controllo "else if(flag==2)"
	 * perchè avrebbe lanciato un IllegalArgumentException interrompendo il flusso
	 * di esecuzione.
	 */
	
	/*
	 * Test4
	 * Parametri:
	 * 	flag = 2,
	 * 	idRequest = 2,
	 * 	cfu = 3.
	 * Creo un utente e una richiesta (con id=2) e verifico il comportamento della servlet
	 * con esecuzione della query che va a buon fine.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test4() throws ServletException, IOException {
		request.addParameter("flag", "2");
		request.addParameter("idRequest", "2");
		request.addParameter("cfu", "3");
		
		inserisciUtente();
		inserisciRequest2();
		
		servlet.doGet(request, response);
			
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	/*
	 * Test5
	 * Parametri:
	 * 	flag = 2,
	 * 	idRequest = 999 (ERRORE!),
	 * 	cfu = 3.
	 * Creo un utente e una richiesta (con id=2) e verifico il comportamento della servlet
	 * con esecuzione della query che NON va a buon fine.
	 * Infine rimuovo le entità inserite nel database.
	 */
		@Test
		void test5() throws ServletException, IOException {
			request.addParameter("flag", "2");
			request.addParameter("idRequest", "999");
			request.addParameter("cfu", "3");
			
			inserisciUtente();
			inserisciRequest2();
			
			servlet.doGet(request, response);
				
			rimuoviRequest();
			rimuoviUtente();
		}
		
		@Test
		void test10() throws ServletException, IOException {
			request.addParameter("flag", "2");
			request.addParameter("idRequest", "2");
			request.addParameter("cfu", "777");
			
			inserisciUtente();
			inserisciRequest2();
			
			servlet.doGet(request, response);
				
			rimuoviRequest();
			rimuoviUtente();
		}
	
		/*
		 * Nota: Ho trovato un errore durante la creazione dello statement in quanto
		 * venivano settati dei valori errati (o meglio, scambiati) per l'idRequest
		 * e requestWorkingAdminState.
		 * Quindi ho proceduto con scambiare i due valori durante le istruzioni stmt.setInt(x,y);
		 */
		
		/*
		 * Test6
		 * Parametri:
		 * 	flag = 3,
		 * 	idRequest = 2.
		 * Creo un utente e una richiesta (con id=2) e verifico il comportamento della servlet
		 * con esecuzione della query che va a buon fine.
		 * Infine rimuovo le entità inserite nel database.
		 */
	@Test
	void test6() throws ServletException, IOException {
		request.addParameter("flag", "3");
		request.addParameter("idRequest", "2");
		
		inserisciUtente();
		inserisciRequest2();
		
		servlet.doGet(request, response);
			
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	//Flag == 3 e query non buona
	/*
	 * Test7
	 * Parametri:
	 * 	flag = 3,
	 * 	idRequest = 999 (ERRORE!).
	 * Creo un utente e una richiesta (con id=2) e verifico il comportamento della servlet
	 * con esecuzione della query che NON va a buon fine.
	 * Infine rimuovo le entità inserite nel database.
	 */
	@Test
	void test7() throws ServletException, IOException {
		inserisciUtente();
		inserisciRequest2();
		
		request.addParameter("flag", "3");
		request.addParameter("idRequest", "999");
		
		servlet.doGet(request, response);
		
		rimuoviRequest();
		rimuoviUtente();
	}
	
	
	private void inserisciUtente() {
		String insertUser = "insert into user values (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement st = conn.prepareStatement(insertUser);
			st.setString(1, "e.tesauro5@studenti.unisa.it");
			st.setString(2, "Emm");
			st.setString(3, "Tes");
			st.setString(4, String.valueOf("M"));
			st.setString(5, new Utils().generatePwd("Ciao1234"));
			st.setInt(6, 0);
			
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void inserisciRequest1() {
		String insertRequest = "insert into request values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement st = conn.prepareStatement(insertRequest);
			st.setInt(1, 2);
			st.setString(2, "B.6.1231");
			st.setString(3, "C2");
			st.setString(4, "2017-08-19");
			st.setString(5, "2017-08-20");
			st.setString(6, "2019");
			st.setInt(7, 3);
			st.setInt(8, 512105000);
			st.setInt(9, 6);
			st.setString(10, "e.tesauro5@studenti.unisa.it");
			st.setInt(11, 3);
			//Numero di richiesta
			st.setInt(12, 1);
			
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void inserisciRequest2() {
		
		String insertRequest = "insert into request values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement st = conn.prepareStatement(insertRequest);
			st.setInt(1, 2);
			st.setString(2, "B.6.1231");
			st.setString(3, "C2");
			st.setString(4, "2017-08-19");
			st.setString(5, "2017-08-20");
			st.setString(6, "2019");
			st.setInt(7, 3);
			st.setInt(8, 512105000);
			st.setInt(9, 6);
			st.setString(10, "e.tesauro5@studenti.unisa.it");
			st.setInt(11, 3);
			//Numero di richiesta
			st.setInt(12, 2);
			
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void rimuoviUtente() {
		String sql = "delete from user where email = ?";
		
		try {
			PreparedStatement st  = conn.prepareStatement(sql);
			st.setString(1, "e.tesauro5@studenti.unisa.it");
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void rimuoviRequest() {
		
		String sql = "delete from request where FK_USER = ?";
		
		try {
			PreparedStatement st  = conn.prepareStatement(sql);
			st.setString(1, "e.tesauro5@studenti.unisa.it");
			st.executeUpdate();
			conn.commit();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserisciAttached1() {
	
		String sql = " INSERT INTO attached (id_attached, filename, fk_request, fk_user) VALUES (?, ?, ?, ?) ";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, 100);
			st.setString(2, "Allegato1.pdf");
			st.setInt(3, 2);
			st.setString(4, "e.tesauro5@studenti.unisa.it");
			st.executeUpdate();
			conn.commit();
			st.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inserisciAttached2() {
	
			String sql = " INSERT INTO attached (id_attached, filename, fk_request, fk_user) VALUES (?, ?, ?, ?) ";
			
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, 101);
				st.setString(2, "Allegato2.pdf");
				st.setInt(3, 2);
				st.setString(4, "e.tesauro5@studenti.unisa.it");
				st.executeUpdate();
				conn.commit();
				st.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

	public void removeAttached() throws SQLException {
		
		String sql = "delete from attached where FK_user = ?";
		
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, "e.tesauro5@studenti.unisa.it");
		st.executeUpdate();
		conn.commit();
		st.close();
	}
}
