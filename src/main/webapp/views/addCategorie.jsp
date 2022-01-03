  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="${baseURL}/views/assets/vendor/jquery.min.js"></script>
<style type="text/css">
	input {
	  display: block;
	   border: none;
 	 border-bottom: 1px solid ;
	}
	ul, #myUL {
	  list-style-type: none;
	}
	
	/* Remove margins and padding from the parent ul */
	#myUL {
	  margin: 0;
	  padding: 0;
	}
	
	/* Style the caret/arrow */
	.caret {
	  cursor: pointer;
	  user-select: none; /* Prevent text selection */
	}
	
	/* Create the caret/arrow with a unicode, and style it */
	.caret::before {
	  content: "\25B6";
	  color: black;
	  display: inline-block;
	  margin-right: 6px;
	}
	
	/* Rotate the caret/arrow icon when clicked on (using JavaScript) */
	.caret-down::before {
	  transform: rotate(90deg);
	}
	
	/* Hide the nested list */
	.nested {
	  display: none;
	}
	
	/* Show the nested list when the user clicks on the caret/arrow (with JavaScript) */
	.active {
	  display: block;
	}
</style>


<script>    
	var count=0;
	const categories = [];
	const sousCategories = [];
	
    function add_categorie() 
    {
    	if (count==0){
    		var node = document.createElement("LI");
        	var input = document.createElement("input");
        	input.setAttribute('type', 'text');
            input.setAttribute('id', 'inputCat');
            
        	  node.appendChild(input);
        	  document.getElementById("cat").appendChild(node);
        	  
    	}

  	  var txtbox = document.getElementById('inputCat');
  	    txtbox.onkeydown = function(e) {
  	      if (e.key == "Enter") {
  	        console.log('enter key pressed');
  	      }
  	      e.preventDefault();
  	    };
      	count+=1;
    };
    var count2=0;
    function add_souscategorie() 
    {
    	if (count2==0){
    		var node = document.createElement("LI");
        	var input = document.createElement("input");
        	input.setAttribute('type', 'text');
            input.setAttribute('id', 'inputSousCat');
            
        	  node.appendChild(input);
        	  document.getElementById("sousCat").appendChild(node);
    	}
    	
      	count2+=1;
    };
    
   
	</script>
	
</head>
<body>

 <ul id="cat">
  <li><span class="caret">Beverages</span>
    <ul class="nested" id="sousCat">
      <li>Water</li>
      <li>Coffee</li>
      <li>Tea</li>
      <li><button type="button" onclick="add_souscategorie()">ajout sous-categorie</button></li>
    </ul>
  </li>
  <li>
  <button type="button" onclick="add_categorie()">ajout categorie</button>
  </li>
</ul> 


	<script>
	var toggler = document.getElementsByClassName("caret");
	var i;
	  
	for (i = 0; i < toggler.length; i++) {
	  toggler[i].addEventListener("click", function() {
	    this.parentElement.querySelector(".nested").classList.toggle("active");
	    this.classList.toggle("caret-down");
	  });
	} 
</script>
</body>
</html>