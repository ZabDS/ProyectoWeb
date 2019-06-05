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
var Preguntas=[];
var rows = [];
var Name;

for(i in PreguntasD){
   rows.push(createData(PreguntasD[i]));
}

class Index extends React.Component {
    
   GetCheck(){
       if(event.target.checked)
           if(Preguntas.indexOf(event.target.value) == -1)
            Preguntas.push(event.target.value);
	}
        
   GetName = event => {
     Name=event.target.value;
  };
   
   CrearEx(){
       window.location.href="CrearExamen?Quests="+Preguntas.toString()+"&TestName="+Name;
   }
   
        
  render() {
    const { classes } = this.props;
    return (
      <MuiThemeProvider theme={theme}>  
          <TextField
                id="TestName"
                className={classes.textField}
                margin="normal"
                label="Nombre de Examen"
                onChange={this.GetName}
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
            <TableRow key={row.name} role="checkbox">
                 <tableCell>
                 <Checkbox value={row.name} onChange={this.GetCheck}></Checkbox>
                 </tableCell>
              <TableCell component="th" scope="row">
                {row.name}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Button variant="contained" className={classes.button} color="primary" onClick={this.CrearEx}>
        Crear
      </Button>
    </Paper>
      </MuiThemeProvider>
    );
  }
}

const App = withStyles(styles)(Index);

ReactDOM.render(<App />, 
document.getElementById('ModificarExamen'));
