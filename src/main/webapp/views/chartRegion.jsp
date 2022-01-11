<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/css/ol.css" type="text/css">
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/build/ol.js"></script>
<style>
      .map {
        height: 600px;
        width: 100%;
      }
    </style>
<%-- <script src="${baseURL}/views/assets/v6.10.0-dist/ol.js"></script> --%>
<title></title>
<h2>My Map</h2>
<div id="map" class="map">
	    
</div>
<div id="popup" class="ol-popup" >
	<a href="#" id="popup-closer" class="ol-popup-closer"></a>
	<div id="popup-content" class="col-lg-3"></div>
</div>
<!-- <script src="${baseURL}/views/assets/js/geochart.js">  -->
<script>
window.onload = init;
function init(event){
	var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          })
        ],
        view: new ol.View({
          center: ol.proj.fromLonLat([ 47.50792, -18.8792]),
          zoom: 6
        })
      });
    
    var container = document.getElementById('popup');
    var content = document.getElementById('popup-content');
    var closer = document.getElementById('popup-closer');


    /*var overlay = new ol.Overlay({
        element: content,
        autoPan: false,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);
   
    closer.onclick = function() {
        //overlay.setPosition(undefined);
        closer.blur();
        return false;
    };*/
    
    var listeLayer = [];
    var listeSignalement=[];
    var cordLayer = [];
	var i = 0;
	console.log("${signalements}");
	
    <c:forEach  items="${signalements}" var ="signalement">
    	listeSignalement[i] = "${signalement}";
    	cordLayer[i] =event.coordinate;
		var coord = ol.proj.fromLonLat([${signalement.longitude}, ${signalement.latitude}]);
		listeLayer[i] = new ol.layer.Vector({
	        source: new ol.source.Vector({
	          projection: 'EPSG:4326',
	          features: [new ol.Feature(new ol.geom.Circle(coord, 20000))]
	        }),
	        // style: [
	        //   new ol.style.Style({
	        //     stroke: new ol.style.Stroke({
	        //       color: 'blue',
	        //       width: 3
	        //     }),
	        //     fill: new ol.style.Fill({
	        //       color: 'rgba(0, 0, 255, 0.1)'
	        //     })
	        //   })
	        // ]
	      });
		 
		  map.addLayer(listeLayer[i]);
		  
	      i++;
    </c:forEach>
    console.log(cordLayer);
    

    /*var marker = new ol.layer.Vector({
        source: new ol.source.Vector({
            features: [
                new ol.Feature({
                    geometry: new ol.geom.Point(ol.proj.fromLonLat([47.50792, -18.8792]))
                })
            ]
        })
    });*/
    //map.addLayer(layer);

    //map.addLayer(marker);
	 /*map.on('singleclick', function (event) {
            if (map.hasFeatureAtPixel(event.pixel) === true) {
                let coordinate = event.coordinate;
       			var lfeture=map.getFeaturesAtPixel(event.pixel);
               let temp = "<div class=\"card\"> \
        		<img src=\"${baseURL}/views/assets/img/card.jpg\" width=\"50\" class=\"card-img-top\" alt=\"...\"> \
            	<input type=\"hidden\" id=\"url\" value=\"${baseURL}\"> \
            	<div class=\"card-body\"> \
                <h5 class=\"card-title\">Signalement <span id=\"idSignalement\">${signalement.idSignalement}</span></h5> \
                <p class=\"card-text\">Description: ${signalement.description}</p> \
                <p class=\"card-text\">Date: ${signalement.dateSignalement}</p> \
                <hr> \
                <p class=\"card-text\">Longitude: ${signalement.longitude}</p> \
                <p class=\"card-text\">Latitude: ${signalement.latitude}</p> \
                <hr> \
                <select name=\"idRegion\" id=\"idRegion\">";
                
                <c:forEach  items="${regions}" var ="region"> 
                temp+= "<option value=\"${region.idRegion}\">${region.nom}</option>";
                </c:forEach>
                
           		temp+= "</select> \
                <button id=\"bouton\">Affecter</button> \
                </div>";
                content.innerHTML=temp;
                overlay.setPosition(coordinate);
            } else {
                overlay.setPosition(undefined);
                closer.blur();
            }
        });*/
    
   
    /*var overlay = new ol.Overlay({
        element: content,
        autoPan: false,
        autoPanAnimation: {
            duration: 250
        }
    });
    map.addOverlay(overlay);
   
    closer.onclick = function() {
        overlay.setPosition(undefined);
        closer.blur();
        return false;
    };*/


}
</script>
