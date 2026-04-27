import plotly.express as px # pip install plotly

# IRIS Dataset: https://gist.github.com/curran/a08a1080b88344b0c8a7
# Load the iris dataset
iris = px.data.iris()

# Create a scatter plot of sepal width vs sepal length
fig = px.scatter(iris, x='sepal_width', y='sepal_length',
                 color='species', title='Iris Dataset: Sepal Width vs Sepal Length')

# Show the plot
fig.show()