const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')


let htmlPageNames = ['product','signup','asd','userprofile',"test","test_fragments","single_product","user_products","cart"];
let multipleHtmlPlugins = htmlPageNames.map(name => {
  return new HtmlWebpackPlugin({
    template: `./src/${name}.html`, // relative path to the HTML files
    filename: `${name}.html`, // output HTML files
    chunks: [`${name}`] // respective JS files
  })
});


module.exports = {
	entry: {
		main: './src/index.js',
		product: './src/product.js'
	
	},
	output: {
		path: path.join(__dirname, '/dist/'),
		filename: '[name].js'

	},
	module: {
		rules: [
			{
				test: /\.js$/,
				exclude: /node_modules/,
				use: {
					loader: 'babel-loader'
				}

			}
		]

	},
	plugins: [
		new HtmlWebpackPlugin({
			template: './src/index.html',
			chunks: ['main']

		})
	].concat(multipleHtmlPlugins)


}