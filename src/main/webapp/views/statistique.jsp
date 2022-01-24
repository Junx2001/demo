<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Statistique</h1>
        <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="${baseURL}/back/signalements">Liste</a></li>
               <li class="breadcrumb-item active">Statistique</li>
             </ol>
         </nav>

    </div><!-- End Page Title -->

    <section class="section">
        <div class="row">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Statistique global de signalements par sous-catégorie</h5>

                        <!-- Bar Chart -->
                        <div id="barChart" style="min-height: 365px;"></div>
                        <script>
                            document.addEventListener("DOMContentLoaded", () => {
                            new ApexCharts(document.querySelector("#barChart"), {
                            series: [{
                            data: [
                            <c:forEach  items="${statSousCat}" var ="stat">
                                ${stat.nb},
                            </c:forEach>
                            ]
                            }],
                                    chart: {
                                    type: 'bar',
                                            height: 350
                                    },
                                    plotOptions: {
                                    bar: {
                                    borderRadius: 4,
                                            horizontal: true,
                                    }
                                    },
                                    dataLabels: {
                                    enabled: false
                                    },
                                    xaxis: {
                                    categories: [
                            <c:forEach  items="${statSousCat}" var ="stat">
                                    '${stat.label}',
                            </c:forEach>
                                    ],
                                    }
                            }).render();
                            });
                        </script>
                        <!-- End Bar Chart -->

                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Taux global de signalements par region</h5>

                        <!-- Pie Chart -->
                        <div id="pieChart" style="min-height: 310.7px;"></div>

                        <style type="text/css">	

                            .apexcharts-legend {	
                                display: flex;	
                                overflow: auto;	
                                padding: 0 10px;	
                            }	
                            .apexcharts-legend.apx-legend-position-bottom, .apexcharts-legend.apx-legend-position-top {	
                                flex-wrap: wrap	
                            }	
                            .apexcharts-legend.apx-legend-position-right, .apexcharts-legend.apx-legend-position-left {	
                                flex-direction: column;	
                                bottom: 0;	
                            }	
                            .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-left, .apexcharts-legend.apx-legend-position-top.apexcharts-align-left, .apexcharts-legend.apx-legend-position-right, .apexcharts-legend.apx-legend-position-left {	
                                justify-content: flex-start;	
                            }	
                            .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-center, .apexcharts-legend.apx-legend-position-top.apexcharts-align-center {	
                                justify-content: center;  	
                            }	
                            .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-right, .apexcharts-legend.apx-legend-position-top.apexcharts-align-right {	
                                justify-content: flex-end;	
                            }	
                            .apexcharts-legend-series {	
                                cursor: pointer;	
                                line-height: normal;	
                            }	
                            .apexcharts-legend.apx-legend-position-bottom .apexcharts-legend-series, .apexcharts-legend.apx-legend-position-top .apexcharts-legend-series{	
                                display: flex;	
                                align-items: center;	
                            }	
                            .apexcharts-legend-text {	
                                position: relative;	
                                font-size: 14px;	
                            }	
                            .apexcharts-legend-text *, .apexcharts-legend-marker * {	
                                pointer-events: none;	
                            }	
                            .apexcharts-legend-marker {	
                                position: relative;	
                                display: inline-block;	
                                cursor: pointer;	
                                margin-right: 3px;	
                                border-style: solid;
                            }	

                            .apexcharts-legend.apexcharts-align-right .apexcharts-legend-series, .apexcharts-legend.apexcharts-align-left .apexcharts-legend-series{	
                                display: inline-block;	
                            }	
                            .apexcharts-legend-series.apexcharts-no-click {	
                                cursor: auto;	
                            }	
                            .apexcharts-legend .apexcharts-hidden-zero-series, .apexcharts-legend .apexcharts-hidden-null-series {	
                                display: none !important;	
                            }	
                            .apexcharts-inactive-legend {	
                                opacity: 0.45;	
                            }</style>

                        <script>
                            document.addEventListener("DOMContentLoaded", () => {
                            new ApexCharts(document.querySelector("#pieChart"), {
                            series: [
                            <c:forEach  items="${statRegion}" var ="stat">
                                ${stat.nb},
                            </c:forEach>
                            ],
                                    chart: {
                                    height: 350,
                                            type: 'pie',
                                            toolbar: {
                                            show: true
                                            }
                                    },
                                    labels: [
                            <c:forEach  items="${statRegion}" var ="stat">
                                    '${stat.region}',
                            </c:forEach>
                                    ]
                            }).render();
                            });
                        </script>
                        <!-- End Pie Chart -->

                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Statistique Probleme Résolus Par Mois</h5>
                        <input type="hidden" id="url" value="${baseURL}">
                        Choisir une année : <input type="number" class="form-control" id="annee"><br>
                        <button class="btn btn-success" id="bouton">Envoyer</button>

                        <!-- Line Chart -->
                        <canvas id="lineChart" style="max-height: 400px;"></canvas>
                        <script>
                            var myChart;
                            var baseUrl = "";
                            var annee = "";
                            var donn = [];
                            $('#bouton').click(function () {
                            donn = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
                            baseUrl = $('#url').val();
                            annee = $('#annee').val();
                            console.log(baseUrl);
                            console.log(annee);
                            $.ajax({
                            url: baseUrl + '/back/signalements/statParMois/' + annee,
                                    method: 'get',
                                    dataType: 'json',
                                    success: function (response) {
                                    console.log("get method successfully done");
                                    console.log(response);
                                    for (let i = 0; i < response.length; i++)
                                    {
                                    donn[response[i].mois - 1] = response[i].nb;
                                    }

                                   
                                    if(myChart != undefined)
                                    {
                                        myChart.destroy();
                                    }
                                    
                                    
                                    myChart = new Chart(document.querySelector('#lineChart'), {
                                    type: 'line',
                                            data: {
                                            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                                                    datasets: [{
                                                    label: 'Problèmes résolus sur tout le pays',
                                                            data: donn,
                                                            fill: false,
                                                            borderColor: 'rgb(75, 192, 192)',
                                                            tension: 0.1
                                                    }]
                                            },
                                            options: {
                                            scales: {
                                            y: {
                                            beginAtZero: true
                                            }
                                            }
                                            }
                                    });
                                    }
                            });
                            });



                        </script>
                        <!-- End Line CHart -->
                    </div>
                </div>
            </div>


		<div class="col-lg-6">
           <div class="card">
			<div class="card-body">
              <h5 class="card-title">Statistique de signalements par région </h5>
                        Choisir une région:  <select id="idRegion2" class="form-select">
						                        <c:forEach  items="${regions}" var ="region"  >
						                            <option value="${region.idRegion}.${region.nom}">${region.nom}</option>
						                        </c:forEach>
						                    </select></br>
                        <button class="btn btn-success" id="bouton2">Envoyer</button>


              <!-- Column Chart -->
              <div id="columnChart" style="min-height: 365px;"></div>
         	<style type="text/css">	
    	
		      .apexcharts-legend {	
		        display: flex;	
		        overflow: auto;	
		        padding: 0 10px;	
		      }	
		      .apexcharts-legend.apx-legend-position-bottom, .apexcharts-legend.apx-legend-position-top {	
		        flex-wrap: wrap	
		      }	
		      .apexcharts-legend.apx-legend-position-right, .apexcharts-legend.apx-legend-position-left {	
		        flex-direction: column;	
		        bottom: 0;	
		      }	
		      .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-left, .apexcharts-legend.apx-legend-position-top.apexcharts-align-left, .apexcharts-legend.apx-legend-position-right, .apexcharts-legend.apx-legend-position-left {	
		        justify-content: flex-start;	
		      }	
		      .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-center, .apexcharts-legend.apx-legend-position-top.apexcharts-align-center {	
		        justify-content: center;  	
		      }	
		      .apexcharts-legend.apx-legend-position-bottom.apexcharts-align-right, .apexcharts-legend.apx-legend-position-top.apexcharts-align-right {	
		        justify-content: flex-end;	
		      }	
		      .apexcharts-legend-series {	
		        cursor: pointer;	
		        line-height: normal;	
		      }	
		      .apexcharts-legend.apx-legend-position-bottom .apexcharts-legend-series, .apexcharts-legend.apx-legend-position-top .apexcharts-legend-series{	
		        display: flex;	
		        align-items: center;	
		      }	
		      .apexcharts-legend-text {	
		        position: relative;	
		        font-size: 14px;	
		      }	
		      .apexcharts-legend-text *, .apexcharts-legend-marker * {	
		        pointer-events: none;	
		      }	
		      .apexcharts-legend-marker {	
		        position: relative;	
		        display: inline-block;	
		        cursor: pointer;	
		        margin-right: 3px;	
		        border-style: solid;
		      }	
		      	
		      .apexcharts-legend.apexcharts-align-right .apexcharts-legend-series, .apexcharts-legend.apexcharts-align-left .apexcharts-legend-series{	
		        display: inline-block;	
		      }	
		      .apexcharts-legend-series.apexcharts-no-click {	
		        cursor: auto;	
		      }	
		      .apexcharts-legend .apexcharts-hidden-zero-series, .apexcharts-legend .apexcharts-hidden-null-series {	
		        display: none !important;	
		      }	
		      .apexcharts-inactive-legend {	
		        opacity: 0.45;	
		      }
		      </style>
		      
              <script>
              var baseUrl = "";
              var idRegion = "";
              var donn = [];
              var donn2 = [];
              $('#bouton2').click(function () {
            		$('#columnChart').empty();
              donn = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
              donn2 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
              baseUrl = $('#url').val();
              var region = $('#idRegion2').val().split(".");
              idRegion = region[0];
              var nomR = region[1];
              console.log(baseUrl);
              console.log(idRegion);
              console.log(nomR);
              $.ajax({
              		url: baseUrl + '/back/signalements/statParMoisParRegion/' +idRegion,
                      method: 'get',
                      dataType: 'json',
                      success: function (response) {
		                      console.log("get method successfully done");
		                      console.log(response);
			                      for (let i = 0; i < response[0].length; i++)
			                      {
			                      	donn[response[0][i].mois - 1] = response[0][i].nb;
			                      }
			                      for (let i = 0; i < response[1].length; i++)
			                      {
			                      	donn2[response[1][i].mois - 1] = response[1][i].nb;
			                      }
		
		                      
		                      new ApexCharts(document.querySelector("#columnChart"), {
		                          series: [{
		                              name: 'En cours',
		                              data: donn2
		                            },
		                            {
			                              name: 'Résolus',
			                              data: donn
			                         }
		                            ],
		                            chart: {
		                              type: 'bar',
		                              height: 350
		                            },
		                            plotOptions: {
		                              bar: {
		                                horizontal: false,
		                                columnWidth: '55%',
		                                endingShape: 'rounded'
		                              },
		                            },
		                            dataLabels: {
		                              enabled: false
		                            },
		                            stroke: {
		                              show: true,
		                              width: 2,
		                              colors: ['transparent']
		                            },
		                            xaxis: {
		                              categories: ['Jan','Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct','Nov','Dec'],
		                            },
		                            yaxis: {
		                              title: {
		                                text: 'Etat de signalement de la région '+nomR,
		                              }
		                            },
		                            fill: {
		                              opacity: 1
		                            },
		                            tooltip: {
		                              y: {
		                                formatter: function(val) {
		                                  return val
		                                }
		                              }
		                            }
		                          }).render();
                      }
              });
              });
                                  
                      
              </script>
              <!-- End Column Chart -->

            </div>
            </div>
            </div>

        </div>
    </section>

</main>