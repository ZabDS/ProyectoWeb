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
} = MaterialUI;

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
 GetNick = event => {
     Nick=event.target.value;
  };
  GetPass = event => {
     Pass = event.target.value;
  };
  
   handleClick(){
       window.location.href="GetUser?User="+Nick+"&Pass="+Pass;
	}
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
          image="components/resources/HacerExamen.svg"
          title="Crea"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Crea un Nuevo Examen!
          </Typography>
          <Typography component="p">
           Crea un nuevo examen para que tus alumnos lo resuelvan!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="ObtenerPreguntas?">
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
          image="components/resources/VerExamen.svg"
          title="Ver"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Ve los Exámenes que has hecho!
          </Typography>
          <Typography component="p">
           Verifica que tu exámen este bien hecho!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="VerExamen?">
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
          image="components/resources/ModificarExamen.svg"
          title="Modificar Pregunta"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            ¡Modifica Examen!
          </Typography>
          <Typography component="p">
           ¿No te gusto como quedó tu Examen? 
           Modifícala ahora!
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" href="ModificarExamen">
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

    ReactDOM.render(<App />, document.getElementById('MainMenuExamen'));
