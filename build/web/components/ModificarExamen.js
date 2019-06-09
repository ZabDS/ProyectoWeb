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
  textField: {
    marginLeft: theme.spacing.unit,
    marginRight: theme.spacing.unit,
    width: 200,
  },
});


function createData(name) {
  return { name };
}
var i;
var rows = preguntasDisponibles;
var Name;


class Index extends React.Component {
            
  handleChange(event) {
    this.setState({value: event.target.value});
  }
        
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>  
                                                     
        <form className={classes.container} noValidate autoComplete="off" method="POST" action="ModificarExamen">
        <input type="hidden" name="nombreOriginal" defaultValue={nombreDeExamen}/>

          <TextField
                id="TestName"
                name="nombre"
                defaultValue={nombreDeExamen}
                className={classes.textField}
                margin="normal"
                label="Nombre de Examen"
                onChange={this.handleChange}
                />
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
            <TableRow key={row} role="checkbox">
                 <tableCell>
                 <Checkbox value={row} name="checkbox" onChange={this.handleChange} defaultChecked={preguntasEnExamen.includes(row)}></Checkbox>
                 </tableCell>
              <TableCell component="th" scope="row">
                {row}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
       <input type="submit" value="Modificar!"/>
    </form>
         </MuiThemeProvider>

                );
    
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, 
document.getElementById('ModificarExamen'));
