const {
  Button,
  AppBar,
  Toolbar,
  Paper,
  Card,
  CardActionArea,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  MenuIcon,
  TextField,
  Grid,
  colors,
  createMuiTheme,
  CssBaseline,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Icon,
  MuiThemeProvider,
  Typography,
  withStyles,
} = window['material-ui'];

const theme = createMuiTheme({
  palette: {
    primary: {
      light: colors.purple[300],
      main: colors.purple[500],
      dark: colors.purple[700],
    },
    secondary: {
      light: colors.green[300],
      main: colors.green[500],
      dark: colors.green[700],
    },
  },
  typography: {
    useNextVariants: true,
  },
});

const styles = theme => ({
  root: {
      margin : 20,
  },
  card: {
    maxWidth: 500,
    maxHeight: 600,
    padding: theme.spacing.unit * 2,
    textAlign: 'center',
  },
  grids: {
   flexGrow: 1,
  },
  media: {
    height: 300,
  },
  grow: {
    flexGrow: 1,
  },
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
});

class Index extends React.Component {
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>
        <div className={classes.root}>
          <CssBaseline />
          <Grid container spacing={40} className={classes.grids}>
        <Grid item xs={4}>
         <Card className={classes.card}>
           <CardActionArea>
            <CardMedia
          className={classes.media}
          image="components/resources/CrearPregunta.svg"
          title="Crea"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Crea una Nueva Pregunta!
          </Typography>
          <Typography component="p">
           Crea una pregunta calculada para que tus alumnos la resuelvan!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="CrearPregunta.html">
          Go!
        </Button>
      </CardActions>
         </Card>
         </Grid>

         <Grid item xs={4}>
         <Card className={classes.card}>
           <CardActionArea>
            <CardMedia
          className={classes.media}
          image="components/resources/VerPregunta.svg"
          title="Ver"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Ve las Preguntas que has Creado!
          </Typography>
          <Typography component="p">
           Verifica que la pregunta que has hecho esta bien hecha!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="VerPregunta.html">
          Go!
        </Button>
      </CardActions>
         </Card>
         </Grid>
         
         <Grid item xs={4}>
         <Card className={classes.card}>
           <CardActionArea>
            <CardMedia
          className={classes.media}
          image="components/resources/ModificarPregunta.svg"
          title="Modificar Pregunta"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Modifica Preguntas!
          </Typography>
          <Typography component="p">
           ¿No te gusto como quedó tu pregunta? 
           Modifícala ahora!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="ModificarPregunta.html">
          Go!
        </Button>
      </CardActions>
         </Card>
         </Grid>
         
         <Grid item xs={4}>
         <Card className={classes.card}>
           <CardActionArea>
            <CardMedia
          className={classes.media}
          image="components/resources/Borrar.svg"
          title="Borrar Pregunta"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Borra Preguntas!
          </Typography>
          <Typography component="p">
           ¿No te sirve más tu pregunta? 
           Borrala ahora!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="BorrarPregunta.html">
          Go!
        </Button>
      </CardActions>
         </Card>
         </Grid>
         
         </Grid>
        </div>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, 
document.getElementById('MainMenu'));

