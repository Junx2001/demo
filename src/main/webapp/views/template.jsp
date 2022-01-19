 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>SignalGOV</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${baseURL}/views/assets/img/favicon.png" rel="icon">
  <link href="${baseURL}/views/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${baseURL}/views/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${baseURL}/views/assets/vendor/simple-datatables/style.css" rel="stylesheet">
  <link rel="stylesheet" href="${baseURL}/views/assets/tree/treejs.css" id="treejs_styles">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
  
  <!-- Template Main CSS File -->
  <link href="${baseURL}/views/assets/css/style.css" rel="stylesheet">
  <script src="${baseURL}/views/assets/tree/tree.js"></script>

</head>


<body>
     <script src="${baseURL}/views/assets/vendor/jquery.min.js"></script>
    
    <jsp:include page="header.jsp" />
    <jsp:include page="sidebar.jsp" />
    <jsp:include page="${maPage}.jsp" />
    <jsp:include page="footer.jsp" /> 
    
    

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="${baseURL}/views/assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/chart.js/chart.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/echarts/echarts.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/quill/quill.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="${baseURL}/views/assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="${baseURL}/views/assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="${baseURL}/views/assets/js/main.js"></script>

</body>

</html>