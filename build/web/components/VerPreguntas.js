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
  Checkbox,
  IconButton,
  MenuIcon,
  TextField,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
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
    width: '100%',
    marginTop: theme.spacing(3),
    overflowX: 'auto',
  },
  table: {
    minWidth: 650,
  },
  button: {
    margin: theme.spacing(1),
  },
});


function createData(name) {
  return { name };
}

const rows = preguntasDisponibles;

class Index extends React.Component {
    
   GetCheck(){
       if(event.target.checked)
       console.log(event.target.value); 
	}
        
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>
        <Paper className={classes.root}>
      <Table className={classes.table}>
        <TableHead>
          <TableRow>
            <TableCell padding="checkbox"></TableCell>
            <TableCell>Pregunta</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map(row => (
            <TableRow key={row}>
              <TableCell>
                {row}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Button variant="contained" className={classes.button} color="primary">
        Crear
      </Button>
    </Paper>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, 
document.getElementById('VerPreguntas'));
