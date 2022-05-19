module.exports = {
    outputDir: 'build/dist/public',
    devServer: {
        proxy: {
            "^/games": {
                target: "http://localhost:8080",
                ws: true,
                changeOrigin: true
            }
        }
    }
}

