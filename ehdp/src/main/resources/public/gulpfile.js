var gulp = require('gulp');

var connect = require('gulp-connect');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-minify-css');
var clean = require('gulp-clean');
var runSequence = require('run-sequence');
var browserify = require('gulp-browserify');
var concat = require('gulp-concat');

gulp.task('lint', function() {
	gulp.src(['./app/**/*.js', '!./app/bower_components/**'])
		.pipe(jshint({ multistr: true }))
		.pipe(jshint.reporter('default'))
		.pipe(jshint.reporter('fail'));
});

gulp.task('clean', function() {
	gulp.src('./dist/*')
		.pipe(clean({force: true}));
	gulp.src('./app/js/bundled.js')
		.pipe(clean({force: true}));
});

gulp.task('minify-css', function() {
	var opts = {comments:true,spare:true};
	gulp.src(['./app/**/*.css', '!./app/bower_components/**'])
		.pipe(minifyCSS(opts))
		.pipe(concat('main.css'))
		.pipe(gulp.dest('./dist/css'))
});

gulp.task('minify-js', function() {
	gulp.src(['./app/**/*.js', '!./app/bower_components/**'])
		.pipe(uglify({
			// inSourceMap:
			// outSourceMap: "app.js.map"
		}))
		.pipe(gulp.dest('./dist/'))
});

gulp.task('copy-bower-components', function () {
	gulp.src('./app/bower_components/**')
		.pipe(gulp.dest('dist/bower_components'));
});

gulp.task('copy-html-files', function () {
	gulp.src('./app/**/*.html')
		.pipe(gulp.dest('dist/'));
});

gulp.task('copy-images', function() {
	gulp.src('./app/img/**/*.jpg')
		.pipe(gulp.dest('dist/img'));
});

gulp.task('connect', function () {
	connect.server({
		root: 'app/',
		port: 8080
	});
});

gulp.task('connectDist', function () {
	connect.server({
		root: 'dist/',
		port: 8081
	});
});

gulp.task('browserify', function() {
	gulp.src(['app/js/main.js'])
		.pipe(browserify({
			insertGlobals: true,
			debug: true
		}))
		.pipe(concat('bundled.js'))
		.pipe(gulp.dest('./app/js'))
});

gulp.task('browserifyDist', function() {
	gulp.src(['app/js/main.js'])
		.pipe(browserify({
			insertGlobals: true,
			debug: true
		}))
		.pipe(concat('bundled.js'))
		.pipe(gulp.dest('./dist/js'))
});

gulp.task('default',
	['lint', 'browserify', 'connect']
);

gulp.task('build', function() {
	runSequence(
		['clean'],
		['lint', 'minify-css', 'browserifyDist', 'copy-images', 'copy-html-files', 'copy-bower-components', 'connectDist']
	);
});
