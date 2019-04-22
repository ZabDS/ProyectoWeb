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
      position: 'absolute', 
        left: '50%', 
        top: '50%',
        transform: 'translate(-50%, -50%)',
  },
  card: {
    maxWidth: 500,
    padding: theme.spacing.unit * 2,
    textAlign: 'center',
  },
  paper: {
    padding: theme.spacing.unit * 2,
    maxWidth: 650,
    textAlign: 'center',
    color: 'white',
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
var Nick = ""; 
var Pass = "";
var toServlet;
class Index extends React.Component {  
 GetNick = event => {
     Nick=event.target.value;
    console.log(Nick);
  };
  GetPass = event => {
     Pass = event.target.value;
    console.log(Pass);
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
        <Grid container spacing={0} justfy="center" alignItems="center">
        <Grid item xs={0}>
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
                onChange={this.GetNick}
                />
                <TextField
                id="Password"
                className={classes.textField}
                margin="normal"
                label="Contraseña"
                type="password"
                onChange={this.GetPass}
                />
           </form>
          </Typography>
        </CardContent>
           </CardActionArea>
           <CardActions>
        <Button color="primary" onClick={this.handleClick}>
          Login
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

ReactDOM.render(<App />, document.getElementById('Login'));
