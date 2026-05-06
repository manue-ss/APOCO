<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Hampones - APOCO</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/manage-style.css">
    <style>
        /* Un pequeño estilo extra por si quieres que las filas se vean ordenadas */
        .fila-auditorio {
            display: flex;
            gap: 15px;
            margin-bottom: 15px;
            justify-content: center;
        }
    </style>
</head>

<body>
    <div class="main-container">
        <h1>Auditorio de Hampones</h1>

        <!-- Tabla con los datos originales (Sin ordenar) -->
        <section class="module">
            <h2>Lista de Hampones (Sin Ordenar)</h2>
            <table class="tabla-estandar">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Edad</th>
                        <th>Dinero Dispuesto a Robar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hampon" items="${listaOriginal}">
                        <tr>
                            <td>${hampon.nombre}</td>
                            <td>${hampon.edad} años</td>
                            <td><fmt:formatNumber value="${hampon.dineroARobar}" type="currency" currencySymbol="$" maxFractionDigits="0"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Auditorio con los datos ya ordenados -->
        <section class="module">
            <h2>Vista del Auditorio (Ordenado)</h2>
            <p>Mostrando auditorio con <strong>${columnas}</strong> sillas por fila.</p>
            
            <div class="auditorio">
                <c:forEach var="hampon" items="${listaOrdenada}" varStatus="status">
                    
                    <%-- RUPTURA DE COLUMNA: Si el índice es múltiplo de la cantidad de columnas, abrimos una nueva fila --%>
                    <c:if test="${status.index % columnas == 0}">
                        <div class="fila-auditorio">
                    </c:if>

                    <!-- Silla individual -->
                    <div class="silla">
                        <%-- Pequeño truco para variar la cara según si el índice es par o impar --%>
                        <div class="cara">${status.index % 2 == 0 ? '🦹‍♂️' : '🦹‍♀️'}</div>
                        <div class="details">
                            <strong>Nombre:</strong> ${hampon.nombre}<br>
                            <strong>Edad:</strong> ${hampon.edad} años<br>
                            <strong>Robado:</strong> <fmt:formatNumber value="${hampon.dineroARobar}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
                        </div>
                    </div>

                    <%-- RUPTURA DE COLUMNA: Si es el último elemento de la fila O es el último elemento en total, cerramos la fila --%>
                    <c:if test="${(status.index + 1) % columnas == 0 || status.last}">
                        </div> <!-- Cierra .fila-auditorio -->
                    </c:if>
                    
                </c:forEach>
            </div>
        </section>

        <!-- Comparativa de algoritmos y Ranking de tiempos -->
        <section class="module comparativa-resultados">
            <h2>🏆 Rendimiento de Algoritmos</h2>
            <p class="descripcion-ranking">Podio de los 3 mejores tiempos y registro completo de ejecución.</p>

            <!-- Podio visual (Top 3) -->
            <div class="podio-contenedor">
                <div class="podio-nivel plata" style="height: 150px;">
                    <div class="podio-puesto">🥈 2</div>
                    <div class="details">
                        <strong>${resultadosPrueba[1].nombreAlgoritmo}</strong>
                    </div>
                </div>

                <div class="podio-nivel oro" style="height: 210px;">
                    <div class="podio-puesto">🥇 1</div>
                    <div class="details">
                        <strong>${resultadosPrueba[0].nombreAlgoritmo}</strong>
                    </div>
                </div>

                <div class="podio-nivel bronce" style="height: 100px;">
                    <div class="podio-puesto">🥉 3</div>
                    <div class="details">
                        <strong>${resultadosPrueba[2].nombreAlgoritmo}</strong>
                    </div>
                </div>
            </div>

            <!-- Tabla detallada de los resultados de los algoritmos -->
            <table class="tabla-estandar tabla-ranking">
                <thead>
                    <tr>
                        <th>Puesto</th>
                        <th>Algoritmo</th>
                        <th>Tiempo de Ejecución</th>
                        <th>Iteraciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="resultado" items="${resultadosPrueba}" varStatus="status">
                        <tr class="${status.index == 0 ? 'puesto-oro' : (status.index == 1 ? 'puesto-plata' : (status.index == 2 ? 'puesto-bronce' : ''))}">
                            <td>
                                <c:choose>
                                    <c:when test="${status.index == 0}">🥇 1</c:when>
                                    <c:when test="${status.index == 1}">🥈 2</c:when>
                                    <c:when test="${status.index == 2}">🥉 3</c:when>
                                    <c:otherwise>${status.index + 1}</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${resultado.nombreAlgoritmo}</td>
                            <!-- Conversión de nanosegundos a milisegundos -->
                            <td><fmt:formatNumber value="${resultado.tiempo / 1000000}" maxFractionDigits="3"/> ms</td>
                            <td><fmt:formatNumber value="${resultado.iteraciones}" type="number"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Enlace para volver al inicio -->
        <!-- Asegúrate de cambiar esto a index.jsp si tienes variables dinámicas en el index -->
        <a href="index.html" class="back-link">← Volver al inicio</a>
    </div>
</body>

</html>