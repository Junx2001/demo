<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/css/ol.css" type="text/css">
<script src="https://cdn.jsdelivr.net/gh/openlayers/openlayers.github.io@master/en/v6.10.0/build/ol.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
<style>
    .map {
        height: 600px;
        width: 100%;
    }
    .popover{
	    max-width: 100%; /* Max Width of the popover (depending on the container!) */
	}
</style>
<%-- <script src="${baseURL}/views/assets/v6.10.0-dist/ol.js"></script> --%>
<title></title>
<h2>My Map</h2>
<div class="overflow-auto">
    <div id="map" class="map"><div id="popup"></div>

    </div>
</div>
</div>

<script>
    window.onload = init;
    function init(event) {
        var map = new ol.Map({
            target: 'map',
            layers: [
                new ol.layer.Tile({
                	maxZoom: 14,
                    source: new ol.source.OSM()
                })
            ],
            view: new ol.View({
                center: ol.proj.fromLonLat([47.50792, -18.8792]),
                zoom: 6
            })
        });

        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');


        var listeLayer = [];
        var listeSignalement = [];
        var cordLayer = [];
        var i = 0;
	let temp;
    <c:forEach  items="${signalements}" var ="signalement">
    temp = "<div class=\"card\"> \
        <img src=\"${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}\" height=\"200\"  class=\"card-img-top\" alt=\"...\"> \
        <input type=\"hidden\" id=\"url\" value=\"${baseURL}\"> \
        <div class=\"card-body\"> \
     <h5 class=\"card-title\">Signalement <span id=\"idSignalement\">${signalement.idSignalement}</span></h5> \
     <p class=\"card-text\">Description: ${signalement.description}</p> \
     <p class=\"card-text\">Date: ${signalement.dateSignalement}</p> \
     <hr> \
     <p class=\"card-text\">Longitude: ${signalement.longitude}</p> \
     <p class=\"card-text\">Latitude: ${signalement.latitude}</p> \
     <hr> \
     <select name=\"idRegion\" id=\"idRegion\" class=\"form-select\">";

	<c:forEach  items="${regions}" var ="region">
	temp += '<option value="${region.idRegion}">${region.nom}</option>'
	</c:forEach>
	
	temp += '</select> '+
	  '<button  type="button" class="btn btn-info" id="bouton">Affecter</button> '+
	  '</div>';
        listeSignalement[i] = "${signalement}";
        cordLayer[i] = event.coordinate;
        var coord = ol.proj.fromLonLat([${signalement.longitude}, ${signalement.latitude}]);
        listeLayer[i] = new ol.layer.Vector({
            source: new ol.source.Vector({
                projection: 'EPSG:4326',
                features: [new ol.Feature({
                    geometry : new ol.geom.Point(coord),
                    contenu: temp,
                    coordinate: coord,
                    })]
            }),

            style: [
            	new ol.style.Style({
                    image: new ol.style.Icon({
                      anchor: [0.5, 0.5],
                      anchorXUnits: 'fraction',
                      anchorYUnits: 'pixels',
                      src: '${baseURL}/views/assets/img/icon.png',
                      scale:0.08,
                    }),
                  })
             ],
        });

        map.addLayer(listeLayer[i]);

        i++;
    </c:forEach>

    map.on('pointermove', function (e) {
    	  const pixel = map.getEventPixel(e.originalEvent);
    	  const hit = map.hasFeatureAtPixel(pixel);
    	});

    const element = document.getElementById('popup');

    const popup = new ol.Overlay({
      element: element,
      positioning: 'bottom-center',
      stopEvent: false,
    });
    map.addOverlay(popup);

   
    map.on('click', function (evt) {
    const feature = map.forEachFeatureAtPixel(evt.pixel, function (feature) {
      return feature;
    });
    if (feature) {
    	/*map.getView().animate({
      	  center: feature.get('coordinate'),
      	  duration: 250,
      	  zoom: map.getView().getZoom()+2,
            });*/
      popup.setPosition(evt.coordinate);
      var newScript = document.createElement("script");
      var inlineScript = document.createTextNode("$('#bouton').click(function () {\
                      var baseUrl = $('#url').val();\
                      var sign = $('#idSignalement').html();\
                      var region = $('#idRegion').val();\
                      setTimeout(() => {location.reload();}, 3000);\
                      $.ajax({\
                          url: baseUrl + '/back/signalement/' + sign,\
                          method: 'put',\
                          data: {region: region},\
                          dataType: 'json',\
                          success: function (response) {\
                              console.log(\"put method successfully done\");\
                              console.log(response);\
                          }\
                      });\
                  });");
      newScript.appendChild(inlineScript);
      container.appendChild(newScript);
      var contentType = feature.get('contenu');
      $(element).popover({
        placement: function (context, source) {
            var position = $(source).position();
    
            if (position.left < 280) {
                return "right";
            }
            if (position.bot < 280) {
                return "top";
            }
    
            if (position.top < 280){
                return "bottom";
            }
            
            else {
                return "left";
            }
        },
        html: true,
        sanitize: false,
        content: contentType,
      });
      
      $(element).popover('update')
      $(element).popover('show');
    } else {
      $(element).popover('dispose');
    }
  });

    map.on('movestart', function () {
    	  $(element).popover('dispose');
    	});


    }
</script>

