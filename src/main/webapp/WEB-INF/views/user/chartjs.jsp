<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>AdminLTE 3 | ChartJS</title>
	<!-- Tell the browser to be responsive to screen width -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Google Font: Source Sans Pro -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
	<%@ include file="../include/head2.jsp"%>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>ChartJS</h1>
					</div>
				</div>
			</div><!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6">
						<!-- DONUT CHART -->
						<div class="card card-danger">
							<div class="card-header">
								<h3 class="card-title">Donut Chart</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
									</button>
									<button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
								</div>
							</div>
							<div class="card-body">
								<canvas id="donutChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col (LEFT) -->
					<div class="col-md-6">
						<!-- BAR CHART -->
						<div class="card card-success">
							<div class="card-header">
								<h3 class="card-title">Bar Chart</h3>

								<div class="card-tools">
									<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i>
									</button>
									<button type="button" class="btn btn-tool" data-card-widget="remove"><i class="fas fa-times"></i></button>
								</div>
							</div>
							<div class="card-body">
								<div class="chart">
									<canvas id="barChart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->

					</div>
					<!-- /.col (RIGHT) -->
				</div>
				<!-- /.row -->
			</div><!-- /.container-fluid -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<footer class="main-footer">
		<div class="float-right d-none d-sm-block">
			<b>Version</b> 3.0.5
		</div>
		<strong>Copyright &copy; 2014-2019 <a href="http://adminlte.io">AdminLTE.io</a>.</strong> All rights
		reserved.
	</footer>

	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Add Content Here -->
	</aside>
	<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<%@ include file="../include/plugin_js.jsp"%>
<!-- OPTIONAL SCRIPTS -->
<script src="${path }/plugins/chart.js/Chart.min.js"></script>
<!-- page script -->
<script>
	$(function () {
		/* ChartJS
         * -------
         * Here we will create a few charts using ChartJS
         */

		//-------------
		//- DONUT CHART -
		//-------------
		// Get context with jQuery - using jQuery's .get() method.
		var donutChartCanvas = $('#donutChart').get(0).getContext('2d')
		var donutData        = {
			labels: [
				'눈 감김',
				'눈 깜빡임',
				'하품',
				'운전자 이탈'
			],
			datasets: [
				{
					data: ["${logArray[0]}","${logArray[1]}","${logArray[2]}","${logArray[3]}"], // 여기에 데이터를 채워 넣으면 된다.
					backgroundColor : ['#f56954', '#00a65a', '#f39c12', '#00c0ef'],
				}
			]
		}
		var donutOptions     = {
			maintainAspectRatio : false,
			responsive : true,
		}
		//Create pie or douhnut chart
		// You can switch between pie and douhnut using the method below.
		var donutChart = new Chart(donutChartCanvas, {
			type: 'doughnut',
			data: donutData,
			options: donutOptions
		})

		//-------------
		//- BAR CHART -
		//-------------
		var areaChartData = {
			labels  : ['눈 감김', '눈 깜빡임', '하품', '운전자 이탈', '전체 평균'],
			datasets: [
				{
					label               : 'Digital Goods',
					backgroundColor     : 'rgba(60,141,188,0.9)',
					borderColor         : 'rgba(60,141,188,0.8)',
					pointRadius          : false,
					pointColor          : '#3b8bba',
					pointStrokeColor    : 'rgba(60,141,188,1)',
					pointHighlightFill  : '#fff',
					pointHighlightStroke: 'rgba(60,141,188,1)',
					data                : ["${logArray[0]}", "${logArray[1]}", "${logArray[2]}",
						"${logArray[3]}", "${logArray[4]}"]
				},
				{
					label               : '현재 졸음 인식률',
					backgroundColor     : 'rgba(210, 214, 222, 1)',
					borderColor         : 'rgba(210, 214, 222, 1)',
					pointRadius         : false,
					pointColor          : 'rgba(210, 214, 222, 1)',
					pointStrokeColor    : '#c1c7d1',
					pointHighlightFill  : '#fff',
					pointHighlightStroke: 'rgba(220,220,220,1)',
					data                : [65, 59, 80, 81, 56, 55, 40]
				},
			]
		}

		var barChartCanvas = $('#barChart').get(0).getContext('2d')
		var barChartData = jQuery.extend(true, {}, areaChartData)
		var temp0 = areaChartData.datasets[0]
		var temp1 = areaChartData.datasets[1]
		barChartData.datasets[0] = temp1
		barChartData.datasets[1] = temp0

		var barChartOptions = {
			responsive              : true,
			maintainAspectRatio     : false,
			datasetFill             : false
		}

		var barChart = new Chart(barChartCanvas, {
			type: 'bar',
			data: barChartData,
			options: barChartOptions
		})

	})
</script>
</body>
</html>