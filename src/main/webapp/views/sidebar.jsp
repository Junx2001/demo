 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/signalement/statistique">
          <i class="bi bi-grid"></i>
          <span>Statistique</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/categorie">
          <i class="bi bi-menu-button-wide"></i>
          <span>CRUD catégories</span>
        </a>
      </li>
      
      <li class="nav-item">
        <a class="nav-link " href="${baseURL}/signalement">
          <i class="bi bi-layout-text-window-reverse"></i>
          <span>Gestion des signalements</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-login.html">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>Login</span>
        </a>
      </li><!-- End Login Page Nav -->

    </ul>

  </aside><!-- End Sidebar-->