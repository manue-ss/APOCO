<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <title>Políticos - APOCO</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/manage-style.css">
</head>

<body>
    <div class="main-container">
        <h1>Gestión de Políticos</h1>
        <p>Mostrando los resultados para <strong>${cantidad}</strong> datos generados.</p>

        <!-- Datos originales (Sin ordenar) -->
        <section class="module">
            <h2>Registro Actual (Sin Ordenar)</h2>
            <table class="tabla-estandar">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Dinero robado</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iteramos sobre la lista original -->
                    <c:forEach var="corrupto" items="${listaOriginal}">
                        <tr>
                            <td>${corrupto.nombre}</td>
                            <td><fmt:formatNumber value="${corrupto.dineroRobado}" type="currency" currencySymbol="$" maxFractionDigits="0"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Datos procesados (Ordenados) -->
        <section class="module">
            <h2>Tabla Ordenada</h2>
            <table class="tabla-estandar">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Dinero robado</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iteramos sobre la lista ordenada -->
                    <c:forEach var="corrupto" items="${listaOrdenada}">
                        <tr>
                            <td>${corrupto.nombre}</td>
                            <td><fmt:formatNumber value="${corrupto.dineroRobado}" type="currency" currencySymbol="$" maxFractionDigits="0"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Ranking y rendimiento de algoritmos -->
        <section class="module comparativa-resultados">
            <h2>🏆 Rendimiento de Algoritmos</h2>
            <p class="descripcion-ranking">Podio de los 3 mejores tiempos y registro completo de ejecución.</p>

            <!-- Contenedor visual del Top 3 (Podio) -->
            <!-- Utilizamos los índices [0], [1] y [2] asumiendo que la lista viene ordenada del más rápido al más lento -->
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

            <!-- Tabla comparativa de tiempos e iteraciones -->
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
                    <!-- Usamos varStatus="status" para saber en qué posición del ranking estamos -->
                    <c:forEach var="resultado" items="${resultadosPrueba}" varStatus="status">
                        <!-- Asignamos la clase CSS dependiendo del puesto -->
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
                            <!-- Convertimos los nanosegundos a milisegundos dividiendo por 1,000,000 -->
                            <td><fmt:formatNumber value="${resultado.tiempo / 1000000}" maxFractionDigits="3"/> ms</td>
                            <td><fmt:formatNumber value="${resultado.iteraciones}" type="number"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

        <!-- Enlace para regresar al inicio -->
        <a href="index.html" class="back-link">← Volver al inicio</a>
    </div>
</body>

</html>