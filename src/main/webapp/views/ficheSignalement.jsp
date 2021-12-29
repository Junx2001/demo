<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

<main id="main" class="main">
    <div class="pagetitle">
      <h1>Cards</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item">Components</li>
          <li class="breadcrumb-item active">Cards</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    
		<div>

          <!-- Card with an image on top -->
          <div class="card">
            <img src="${baseURL}/views/assets/img/card.jpg" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Description</h5>
              <p class="card-text">${signalement[0][2]}</p>
            </div>
          </div><!-- End Card with an image on top -->


        </div>
      </main>