/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package co.edu.udistrital.controller;

import co.edu.udistrital.model.dto.ResultadoPrueba;
import co.edu.udistrital.model.entities.Hampon;
import co.edu.udistrital.services.TestAlgoritmosService;
import co.edu.udistrital.util.algoritmos.OrdenamientoBurbuja;
import co.edu.udistrital.util.algoritmos.OrdenamientoInsercion;
import co.edu.udistrital.util.algoritmos.OrdenamientoMerge;
import co.edu.udistrital.util.algoritmos.OrdenamientoQuickSort;
import co.edu.udistrital.util.generador.GeneradorHampones;
import co.edu.udistrital.util.listas.ListaEnlazadaSimple;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer-Pc
 */
@WebServlet(name = "HamponesServlet", urlPatterns = {"/HamponesServlet"})
public class HamponesServlet extends HttpServlet {

    private final TestAlgoritmosService<Hampon> testService = new TestAlgoritmosService<>();
    private final GeneradorHampones generador = new GeneradorHampones();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String filasStr = request.getParameter("filas");
            int filas = Integer.parseInt(filasStr);
            String columnasStr = request.getParameter("columnas");
            int columnas = Integer.parseInt(columnasStr);
            int cantidad = filas * columnas;

            ListaEnlazadaSimple<Hampon> lista = generador.generarLista(cantidad);

            List<ResultadoPrueba> resultados = new ArrayList<>();

            resultados.add(testService.test(lista, new OrdenamientoBurbuja<>(), "Burbuja"));
            resultados.add(testService.test(lista, new OrdenamientoInsercion<>(), "Inserción"));
            resultados.add(testService.test(lista, new OrdenamientoMerge<>(), "Merge Sort"));
            resultados.add(testService.test(lista, new OrdenamientoQuickSort<>(), "Quick Sort"));

            // --- INICIO DEL MINI BURBUJA ---
            // Ordenamos la lista 'resultados' de menor a mayor basándonos en el Tiempo
            int n = resultados.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    // Comparamos el tiempo del actual con el tiempo del siguiente
                    if (resultados.get(j).getTiempo() > resultados.get(j + 1).getTiempo()) {
                        // Intercambiamos (Swap)
                        ResultadoPrueba temp = resultados.get(j);
                        resultados.set(j, resultados.get(j + 1));
                        resultados.set(j + 1, temp);
                    }
                }
            }
            // --- FIN DEL MINI BURBUJA ---

            List<Hampon> listaOriginal = lista.toStandardList();
            ListaEnlazadaSimple<Hampon> listaEnlazadaOrdenada = lista.clonarLista();
            listaEnlazadaOrdenada.ordenar(new OrdenamientoQuickSort<>());
            List<Hampon> listaOrdenada = listaEnlazadaOrdenada.toStandardList();

            request.setAttribute("listaOriginal", listaOriginal);
            request.setAttribute("listaOrdenada", listaOrdenada);
            request.setAttribute("columnas", columnas);
            request.setAttribute("filas", filas);
            request.setAttribute("resultadosPrueba", resultados);

            request.getRequestDispatcher("hampones.jsp").forward(request, response);

        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Por favor, ingresa un n mero v lido.");
            request.getRequestDispatcher("index.html").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
