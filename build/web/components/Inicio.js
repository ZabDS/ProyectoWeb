const {
  Button,
  AppBar,
  Toolbar,
  IconButton,
  MenuIcon,
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
    type:'dark',
    primary: {
      light: colors.red[300],
      main: colors.red[500],
      dark: colors.red[700],
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
  },
  grow: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: 'auto',
  },
});

class Index extends React.Component {
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>
        <div className={classes.root}>
          <CssBaseline />
          <AppBar position="static" color="primary">
        <Toolbar>
          <Typography variant="h4" color="inherit" className={classes.grow}>
            Comenzemos
          </Typography>
          <Button color="inherit" href="Login.html">Login</Button>
        </Toolbar>
      </AppBar>
      <div className="App">
        <header className="App-header">
          <img src="components/resources/logo.svg" className="App-logo" alt="logo" />
        </header>
      </div>
        </div>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, document.getElementById('Inicio'));