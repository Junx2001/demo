 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/back/signalements/statistique">
          <i class="bi bi-graph-up"></i>
          <span>Statistique</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/back/categories">
          <i class="ri-settings-2-fill"></i>
          <span>CRUD catégories</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/back/signalements">
          <i class="bi bi-layout-text-window-reverse"></i>
          <span>Gestion des signalements</span>
        </a>
      </li>

	
	<li class="nav-item">
        <a class="nav-link " href="${baseURL}/back/utilisateurs">
          <i class="bi bi-person-circle"></i>
          <span>Gestion des utilisateurs</span>
        </a>
      </li>
      
    </ul>

  </aside><!-- End Sidebar-->