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
    type:'dark',
    primary: {
      light: colors.blue[300],
      main: colors.blue[500],
      dark: colors.blue[700],
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
    flexGrow: 1,
    margin: 50,
    textAlign: 'center',
  },
  Spaper: {
    height: 300,
    width: 500,
    margin: 20,
    textAlign: 'center',
    rounded: true
  },
  card: {
    maxWidth: 500,
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
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
});



class Index extends React.Component {  
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>
        <div className={classes.root}>
          <CssBaseline />
 
        <Paper className={classes.Spaper} elevation={10}>
         <Card className={classes.card}>
           <CardActionArea>
            <CardMedia
          className={classes.media}
          image="components/resources/lockheart.svg"
          title="Login"
            />
         <CardContent>
          <Typography gutterBottom variant="h5" component="h2">
            Login
          </Typography>
          <Typography component="p">
           <form className={classes.container} noValidate autoComplete="off">
             <TextField
                id="NickName"
                className={classes.textField}
                margin="normal"
                label="Nombre de Usuario"
                />
                <TextField
                id="Password"
                className={classes.textField}
                margin="normal"
                label="Contraseña"
                type="password"
                />
           </form>
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary">
          Login
        </Button>
      </CardActions>
         </Card>
        </Paper>
          
        </div>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, document.getElementById('Login'));
